(ns dove.core
  "Avro schema are constant, spec are designed to be constant. As a
  result, side effects are acceptable in protocol ToSpec. `assert` is
  used more than usual. Wherever an inconsistent state is detected,
  it's prefered to raise a argsual error message rather than unclear
  exception or worse: an erroneous output.

  Reference: https://avro.apache.org/docs/1.8.2/spec.html.
  Any difference should be considered a bug."
  (:require [clojure.spec.alpha :as s]
            [clojure.test.check.generators :as test.g]
            [clj-time.coerce :as tc]
            [camel-snake-kebab.extras :as case.e])
  (:import (org.apache.avro Schema$Field Schema$EnumSchema Schema$NullSchema Schema$BooleanSchema Schema$DoubleSchema Schema$FloatSchema Schema$LongSchema Schema$IntSchema Schema$BytesSchema Schema$StringSchema Schema$FixedSchema Schema$RecordSchema Schema$UnionSchema Schema$MapSchema Schema$ArraySchema LogicalTypes$Date LogicalTypes$TimestampMillis Schema$Type)
           (java.time LocalDate)
           (org.joda.time DateTime)
           (java.nio ByteBuffer)))

(def dove-spec-keyword
  :dove.spec/name)

(def convenient-args
  "These args are not meant to be your default choice, but they are
  somehow convenient to use."
  {:hide-schema-name? false
   :dry-run? false
   :ns-keys? false
   :enum-obj? false
   :required-union-nil-value? false
   :dove.spec/keyword dove-spec-keyword})

(def ignored-specs
  "Spec not to be infered. Useful if you want to use some custom
  specs. Used by to-spec! to keep track of specs and register a spec
  only once."
  (atom #{}))

(defprotocol ToSpec
  (to-spec! [this args] "Recursively infer and register spec of
  record-schema and any nested schemas."))

(defprotocol MapQualifier
  (-qualify-map [schema args] "Recursively qualify keys of a map to
  match some schema namespace."))

(def ->avro-fixed?
  "Sequence of 8-bit unsigned bytes. Returns a singleton of the given
  size."
  (memoize
    (fn [fixed-size]
      (s/with-gen
        #(and (bytes? %)
              (= fixed-size (.limit (ByteBuffer/wrap %))))
        #(test.g/fmap byte-array (test.g/vector test.g/byte fixed-size))))))

(def avro-int?
  "Int: 32-bit signed two's complement integer"
  int?)

(def avro-logical-date?
  "Represents a date within the calendar, with no reference to a
  particular time zone or time of day. Annotates an Avro int, where
  the int stores the number of days from the unix epoch, 1 January
  1970 (ISO calendar). "
  (s/with-gen
    #(instance? LocalDate %)
    (fn [] (test.g/fmap
             #(LocalDate/ofEpochDay %)
             (s/gen avro-int?)))))

(def avro-long?
  "Long: 64-bit signed integer"
  (s/with-gen
    #(instance? Long %)
    #(test.g/fmap
       long
       (test.g/double* {:infinite? false
                        :NaN? false
                        :min Long/MIN_VALUE
                        :max Long/MAX_VALUE}))))

(def avro-logical-timestamp-millis?
  "Represents an instant on the global timeline, independent of a
  particular time zone or calendar, with a precision of one
  millisecond. Annotates an Avro long, where the long stores the
  number of milliseconds from the unix epoch, 1 January 1970
  00:00:00.000 UTC.\n"
  (s/with-gen
    #(instance? DateTime %)
    #(test.g/fmap
       tc/from-long
       (s/gen avro-long?))))

(def avro-float?
  "Imprecise. Single precision (32-bit) IEEE 754 floating-point number"
  (s/with-gen
    #(and (instance? Float %)
          (not (Float/isNaN %))
          (Float/isFinite %))
    #(test.g/fmap
       float
       (test.g/double* {:infinite? false
                        :NaN? false
                        :min Float/MIN_VALUE
                        :max Float/MAX_VALUE}))))

(def avro-double?
  "Imprecise. Double precision (64-bit) IEEE 754 floating-point number"
  (s/with-gen
    #(and (instance? Double %)
          (not (Double/isNaN %))
          (Double/isFinite %))
    #(test.g/fmap
       double
       (test.g/double* {:infinite? false
                        :NaN? false
                        :min Double/MIN_VALUE
                        :max Double/MAX_VALUE}))))

(def enum-obj-spec-value
  (memoize
    (fn [enum-class spec-values]
      (s/with-gen
        #(instance? enum-class %)
        #(test.g/fmap
           (fn [enum-str]
             (Enum/valueOf enum-class enum-str))
           (s/gen (set spec-values)))))))

(def enum-str-spec-value
  (memoize
    (fn [spec-values]

      (set spec-values))))

(def union-spec-symbol
  (memoize
    (fn [spec-names]
      `(s/or
         ~@(mapcat
             (juxt identity
                   identity)
             spec-names)))))

(def array-spec-value
  (memoize
    (fn [spec-keyword]
      (s/coll-of spec-keyword))))

(def map-spec-value
  (memoize
    (fn [spec-keyword]
      (s/map-of string? spec-keyword))))

(def record-spec-symbol
  (memoize
    (fn [args spec-keys]
      (let [spec-name (keyword (:spec-ns args) (:spec-name args))]
        (if (:hide-schema-name? args)
          `(s/keys
             ~(if (:ns-keys? args) :req :req-un) [~@(:required spec-keys)]
             ~(if (:ns-keys? args) :opt :opt-un) [~@(:optional spec-keys)])
          `(s/with-gen
             (s/keys
               ~(if (:ns-keys? args) :req :req-un) [~@(:required spec-keys)]
               ~(if (:ns-keys? args) :opt :opt-un) [~@(:optional spec-keys)])
             #(test.g/fmap
                (fn [value#]
                  (assoc value#
                    ~(:dove.spec/keyword args dove-spec-keyword) ~spec-name))
                (s/gen
                  (s/keys
                    ~(if (:ns-keys? args) :req :req-un) [~@(:required spec-keys)]
                    ~(if (:ns-keys? args) :opt :opt-un) [~@(:optional spec-keys)])))))))))

(defn hierarchy-derive
  [parent descendant]
  (str parent "." descendant))

(defn ignore-spec?
  [args]
  (contains? @ignored-specs (keyword (:spec-ns args) (:spec-name args))))

(defn spec-def
  [args spec-symbol]
  ;; if some default value is defined, return it 5% of the time.
  (cond (:dry-run? args)
        (println "dove: spec definition for" (keyword (:spec-ns args) (:spec-name args)))

        (not (ignore-spec? args))
        (do
          (swap! ignored-specs conj (keyword (:spec-ns args) (:spec-name args)))
          (eval `(s/def
                   ~(keyword (:spec-ns args) (:spec-name args))
                   ~(eval spec-symbol)))))
  (keyword (:spec-ns args) (:spec-name args)))

(def optional-key?
  (memoize
    (fn [field args]
      (if (and (not (:required-union-nil-value? args))
               (->> field
                    .schema
                    .getType
                    (= Schema$Type/UNION))
               (->> field
                    .schema
                    .getTypes
                    (map #(.getType %))
                    (some #(= % Schema$Type/NULL))))
        :optional
        :required))))

(extend-protocol ToSpec
  Schema$StringSchema
  (to-spec! [this args]
    (spec-def args `string?)
    (keyword (:spec-ns args) (:spec-name args)))

  Schema$BytesSchema
  (to-spec! [this args]
    (spec-def args `bytes?)
    (keyword (:spec-ns args) (:spec-name args)))

  Schema$IntSchema
  (to-spec! [this args]
    (let [logical-type (.getLogicalType this)]
      (cond (instance? LogicalTypes$Date logical-type) (spec-def args `avro-logical-date?)
            :default (spec-def args `avro-int?)))
    (keyword (:spec-ns args) (:spec-name args)))

  Schema$LongSchema
  (to-spec! [this args]
    (let [logical-type (.getLogicalType this)]
      (cond (instance? LogicalTypes$TimestampMillis logical-type) (spec-def args `avro-logical-timestamp-millis?)
            :default (spec-def args `avro-long?)))
    (keyword (:spec-ns args) (:spec-name args)))

  Schema$FloatSchema
  (to-spec! [this args]
    (spec-def args `avro-float?)
    (keyword (:spec-ns args) (:spec-name args)))

  Schema$DoubleSchema
  (to-spec! [this args]
    (spec-def args `avro-double?)
    (keyword (:spec-ns args) (:spec-name args)))

  Schema$BooleanSchema
  (to-spec! [this args]
    (spec-def args `boolean?)
    (keyword (:spec-ns args) (:spec-name args)))

  Schema$NullSchema
  (to-spec! [this args]
    (spec-def args `nil?)
    (keyword (:spec-ns args) (:spec-name args)))

  Schema$EnumSchema
  (to-spec! [this args]
    (let [enum-class (Class/forName (.getFullName this))
          spec-keyword (keyword (.getNamespace this)
                                (.getName this))
          spec-values (.getEnumSymbols this)]
      (spec-def (assoc args
                  :spec-ns (.getNamespace this)
                  :spec-name (.getName this))
                (if (:enum-obj? args)
                  `~(enum-obj-spec-value enum-class spec-values)
                  `~(enum-str-spec-value spec-values)))
      (if (and (:spec-ns args)
               (:spec-name args))
        (do (spec-def args `~spec-keyword)
            (keyword (:spec-ns args) (:spec-name args)))
        (keyword (.getNamespace this) (.getName this)))))

  Schema$FixedSchema
  (to-spec! [this args]
    (let [spec-keyword (keyword (.getNamespace this)
                                (.getName this))]
      (spec-def (assoc args
                  :spec-ns (.getNamespace this)
                  :spec-name (.getName this))
                `~(->avro-fixed? (.getFixedSize this)))
      (if (and (:spec-ns args)
               (:spec-name args))
        (do (spec-def args `~spec-keyword)
            (keyword (:spec-ns args) (:spec-name args)))
        (keyword (.getNamespace this) (.getName this)))))

  Schema$MapSchema
  (to-spec! [this args]
    (let [value-schema (.getValueType this)
          spec-name (hierarchy-derive (:spec-name args) (.getName value-schema))
          spec-keyword (to-spec! value-schema (assoc args :spec-name spec-name))]
      (spec-def args
                `~(map-spec-value spec-keyword))
      (keyword (:spec-ns args) (:spec-name args))))

  Schema$ArraySchema
  (to-spec! [this args]
    (let [value-schema (.getElementType this)
          spec-name (hierarchy-derive (:spec-name args) (.getName value-schema))
          spec-keyword (to-spec! value-schema (assoc args :spec-name spec-name))]
      (spec-def args
                `~(array-spec-value spec-keyword))
      (keyword (:spec-ns args) (:spec-name args))))

  Schema$UnionSchema
  (to-spec! [this args]
    (let [spec-names (->> (.getTypes ^Schema$UnionSchema this)
                          (map (fn [schema]
                                 (let [spec-name (hierarchy-derive (:spec-name args) (.getName schema))]
                                   (to-spec! schema (assoc args :spec-name spec-name))))))]
      (spec-def args
                (union-spec-symbol spec-names))
      (keyword (:spec-ns args) (:spec-name args))))

  Schema$Field
  (to-spec! [this args]
    (to-spec! (.schema this)
              (assoc args
                :spec-field this
                :spec-name (.name this))))

  Schema$RecordSchema
  (to-spec! [this args]
    (let [record-fields (.getFields this)
          spec-ns (.getNamespace this)
          spec-name (.getName this)
          spec-keyword (keyword spec-ns spec-name)
          spec-keys (reduce (fn [acc field]
                              (let [spec-key (to-spec! field (assoc args
                                                               :spec-ns (hierarchy-derive spec-ns spec-name)
                                                               :spec-name (.name field)))]
                                (update acc (optional-key? field args) conj spec-key)))
                            {}
                            record-fields)]
      (doseq [field record-fields]
        (to-spec! field (assoc args
                          :spec-ns (hierarchy-derive spec-ns spec-name))))
      (spec-def (assoc args
                  :spec-ns spec-ns
                  :spec-name spec-name)
                (record-spec-symbol (assoc args
                                      :spec-ns spec-ns
                                      :spec-name spec-name)
                                    spec-keys))
      (if (and (:spec-ns args)
               (:spec-name args))
        (do
          (spec-def args `~spec-keyword)
          (keyword (:spec-ns args) (:spec-name args)))
        spec-keyword))))

(extend-protocol MapQualifier
  Schema$MapSchema
  (-qualify-map [schema args]
    (into
      (-> args :value empty)
      (map (fn [[k v]]
             (let [new-v (-qualify-map (.getValueType schema)
                                       (-> args
                                           (update :path conj k)
                                           (assoc :value v)))]
               [k new-v]))
           (-> args :value))))

  Schema$ArraySchema
  (-qualify-map [schema args]
    (into
      (-> args :value empty)
      (map-indexed
        (fn [i v]
          (-qualify-map (.getElementType schema)
                        (-> args
                            (update :path conj i)
                            (assoc :value v))))
        (-> args :value))))

  Schema$UnionSchema
  (-qualify-map [schema args]
    (let [explicit-union-types (:explicit-union-types args)
          _ (assert explicit-union-types "Qualifying a union requires :explicit-union-types.")
          possible-types (.getTypes schema)
          explicit-type (explicit-union-types possible-types (-> args :path reverse) (:value args))
          _ (assert explicit-type "Qualifying a union requires an matching explicit type.")]
      (-qualify-map explicit-type args)))

  Schema$RecordSchema
  (-qualify-map [schema args]
    (let [required-keys (->> schema
                             (.getFields)
                             (map #(.name %)))
          actual-keys (->> (:value args)
                           keys
                           (map name))
          missing-keys (remove (set actual-keys) required-keys)]
      (assert (empty? missing-keys)
              (str "Qualifying data to match a schema requires all schema attributes to be filled."
                   {:path (reverse (:path args))
                    :missing-key missing-keys})))
    (into
      (-> args :value empty)
      (map (fn [[k v]]
             (let [kw-namespace (.getFullName schema)
                   kw-name (name k)
                   field (.getField schema kw-name)
                   new-k (keyword kw-namespace kw-name)
                   new-v (-qualify-map
                           (.schema field)
                           (-> args
                               (assoc :value v)
                               (update :path conj k)))]
               [new-k new-v]))
           (-> args :value))))

  Object
  (-qualify-map [schema args]
    (-> args :value)))

(defn qualify-map
  "Recursively qualify keys of a map to match some schema
  namespace. Example dumb `explicit-union-types`:

  ``` clojure
  (fn [possible-types path value]
    (if value
      (last possible-types)
      (first possible-types)))
  ```"
  [value schema explicit-union-types]
  (-qualify-map schema
                {:value value
                 :explicit-union-types explicit-union-types}))

(def transform-keys
  case.e/transform-keys)

(def unqualify-map
  "Recursively unqualify keys of a map, provided they're keyword."
  #(case.e/transform-keys (comp keyword name) %))
