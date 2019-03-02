(ns dove.core
  "Avro schema are constant, spec are designed to be constant. As a result, side effects are acceptable in protocol ToSpec. `assert` is used more than usual. Wherever an inconsistent state is detected, it's prefered to raise a contextual error message rather than unclear exception or worse: an erroneous output."
  (:require [clojure.spec.alpha :as s]
            [clojure.test.check.generators :as test.g]
            [clj-time.coerce :as tc]
            [camel-snake-kebab.extras :as case.e])
  (:import (org.apache.avro Schema$Field Schema$EnumSchema Schema$NullSchema Schema$BooleanSchema Schema$DoubleSchema Schema$FloatSchema Schema$LongSchema Schema$IntSchema Schema$BytesSchema Schema$StringSchema Schema$FixedSchema Schema$RecordSchema Schema$UnionSchema Schema$MapSchema Schema$ArraySchema LogicalTypes$Date LogicalTypes$TimestampMillis Schema$Type)
           (java.time LocalDate)
           (org.joda.time DateTime)))

(def convenient-args
  "These args are not meant to be your default choice, but they are somehow convenient to use."
  {:hide-schema-name? false
   :dry-run? false
   :ns-keys? false
   :enum-obj? false
   :optional-nil? true})

(def ignored-specs
  "Spec not to be infered. Useful if you want to use some custom specs. Used by to-spec! to keep track of specs and register a spec only once."
  (atom #{}))

(defprotocol ToSpec
  (to-spec! [this args] "Recursively infer and register spec of record-schema and any nested schemas."))

(defprotocol MapQualifier
  (-qualify-map [schema args] "Recursively qualify keys of a map to match some schema namespace."))

(def ->avro-fixed?
  "Sequence of 8-bit unsigned bytes. Returns a singleton of the given size."
  (memoize
    (fn [fixed-size]
      (s/with-gen
        bytes?
        #(test.g/fmap byte-array (test.g/vector test.g/byte fixed-size))))))

(def avro-int?
  "Int: 32-bit signed two's complement integer"
  int?)

(def avro-logical-date?
  "Represents a date within the calendar, with no reference to a particular time zone or time of day. Annotates an Avro int, where the int stores the number of days from the unix epoch, 1 January 1970 (ISO calendar). "
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
       (test.g/double* {:NaN? false
                        :min Long/MIN_VALUE
                        :max Long/MAX_VALUE}))))

(def avro-logical-timestamp-millis?
  "Represents an instant on the global timeline, independent of a particular time zone or calendar, with a precision of one millisecond. Annotates an Avro long, where the long stores the number of milliseconds from the unix epoch, 1 January 1970 00:00:00.000 UTC.\n"
  (s/with-gen
    #(instance? DateTime %)
    #(test.g/fmap
       tc/from-long
       (s/gen avro-long?))))

(def avro-float?
  "Imprecise. Single precision (32-bit) IEEE 754 floating-point number"
  (s/with-gen
    #(instance? Float %)
    #(test.g/fmap
       float
       (test.g/double* {:NaN? false
                        :min Float/MIN_VALUE
                        :max Float/MAX_VALUE}))))

(def avro-double?
  "Imprecise. Double precision (64-bit) IEEE 754 floating-point number"
  (s/with-gen
    #(instance? Double %)
    #(test.g/fmap
       double
       (test.g/double* {:NaN? false
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
    (fn [context spec-keys]
      (let [spec-name (keyword (:spec-ns context) (:spec-name context))]
        (if (:hide-schema-name? context)
          `(s/keys
             ~(if (:ns-keys? context) :req :req-un) [~@(:required spec-keys)]
             ~(if (:ns-keys? context) :opt :opt-un) [~@(:optional spec-keys)])
          `(s/with-gen
             (s/keys
               ~(if (:ns-keys? context) :req :req-un) [~@(:required spec-keys)]
               ~(if (:ns-keys? context) :opt :opt-un) [~@(:optional spec-keys)])
             #(test.g/fmap
                (fn [value#]
                  (assoc value#
                    :dove.spec/name ~spec-name))
                (s/gen
                  (s/keys
                    ~(if (:ns-keys? context) :req :req-un) [~@(:required spec-keys)]
                    ~(if (:ns-keys? context) :opt :opt-un) [~@(:optional spec-keys)])))))))))

(defn hierarchy-derive
  [parent descendant]
  (str parent "." descendant))

(defn ignore-spec?
  [context]
  (contains? @ignored-specs (keyword (:spec-ns context) (:spec-name context))))

(defn spec-def
  [context spec-symbol]
  (cond (:dry-run? context)
        (println "dove: spec definition for" (keyword (:spec-ns context) (:spec-name context)))

        (not (ignore-spec? context))
        (do
          (swap! ignored-specs conj (keyword (:spec-ns context) (:spec-name context)))
          (eval `(s/def
                   ~(keyword (:spec-ns context) (:spec-name context))
                   ~(eval spec-symbol)))))
  (keyword (:spec-ns context) (:spec-name context)))

(def optional-key?
  (memoize
    (fn [field context]
      (if (and (:optional-nil? context)
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
  (to-spec! [this context]
    (spec-def context `string?)
    (keyword (:spec-ns context) (:spec-name context)))

  Schema$BytesSchema
  (to-spec! [this context]
    (spec-def context `bytes?)
    (keyword (:spec-ns context) (:spec-name context)))

  Schema$IntSchema
  (to-spec! [this context]
    (let [logical-type (.getLogicalType this)]
      (cond (instance? LogicalTypes$Date logical-type) (spec-def context `avro-logical-date?)
            :default (spec-def context `avro-int?)))
    (keyword (:spec-ns context) (:spec-name context)))

  Schema$LongSchema
  (to-spec! [this context]
    (let [logical-type (.getLogicalType this)]
      (cond (instance? LogicalTypes$TimestampMillis logical-type) (spec-def context `avro-logical-timestamp-millis?)
            :default (spec-def context `avro-long?)))
    (keyword (:spec-ns context) (:spec-name context)))

  Schema$FloatSchema
  (to-spec! [this context]
    (spec-def context `avro-float?)
    (keyword (:spec-ns context) (:spec-name context)))

  Schema$DoubleSchema
  (to-spec! [this context]
    (spec-def context `avro-double?)
    (keyword (:spec-ns context) (:spec-name context)))

  Schema$BooleanSchema
  (to-spec! [this context]
    (spec-def context `boolean?)
    (keyword (:spec-ns context) (:spec-name context)))

  Schema$NullSchema
  (to-spec! [this context]
    (spec-def context `nil?)
    (keyword (:spec-ns context) (:spec-name context)))

  Schema$EnumSchema
  (to-spec! [this context]
    (let [enum-class (Class/forName (.getFullName this))
          spec-keyword (keyword (.getNamespace this)
                                (.getName this))
          spec-values (.getEnumSymbols this)]
      (spec-def (assoc context
                  :spec-ns (.getNamespace this)
                  :spec-name (.getName this))
                (if (:enum-obj? context)
                  `~(enum-obj-spec-value enum-class spec-values)
                  `~(enum-str-spec-value spec-values)))
      (spec-def context `~spec-keyword)
      (keyword (:spec-ns context) (:spec-name context))))

  Schema$FixedSchema
  (to-spec! [this context]
    (let [spec-keyword (keyword (.getNamespace this)
                                (.getName this))]
      (spec-def (assoc context
                  :spec-ns (.getNamespace this)
                  :spec-name (.getName this))
                `~(->avro-fixed? (.getFixedSize this)))
      (spec-def context
                `~spec-keyword)
      (keyword (:spec-ns context) (:spec-name context))))

  Schema$MapSchema
  (to-spec! [this context]
    (let [value-schema (.getValueType this)
          spec-name (hierarchy-derive (:spec-name context) (.getName value-schema))
          spec-keyword (to-spec! value-schema (assoc context :spec-name spec-name))]
      (spec-def context
                `~(map-spec-value spec-keyword))
      (keyword (:spec-ns context) (:spec-name context))))

  Schema$ArraySchema
  (to-spec! [this context]
    (let [value-schema (.getElementType this)
          spec-name (hierarchy-derive (:spec-name context) (.getName value-schema))
          spec-keyword (to-spec! value-schema (assoc context :spec-name spec-name))]
      (spec-def context
                `~(array-spec-value spec-keyword))
      (keyword (:spec-ns context) (:spec-name context))))

  Schema$UnionSchema
  (to-spec! [this context]
    (let [spec-names (->> (.getTypes ^Schema$UnionSchema this)
                          (map (fn [schema]
                                 (let [spec-name (hierarchy-derive (:spec-name context) (.getName schema))]
                                   (to-spec! schema (assoc context :spec-name spec-name))))))]
      (spec-def context
                (union-spec-symbol spec-names))
      (keyword (:spec-ns context) (:spec-name context))))

  Schema$Field
  (to-spec! [this context]
    (to-spec! (.schema this)
              (assoc context
                :spec-field this
                :spec-name (.name this))))

  Schema$RecordSchema
  (to-spec! [this context]
    (let [record-fields (.getFields this)
          spec-ns (.getNamespace this)
          spec-name (.getName this)
          spec-keyword (keyword spec-ns spec-name)
          spec-keys (reduce (fn [acc field]
                              (let [spec-key (to-spec! field (assoc context
                                                               :spec-ns (hierarchy-derive spec-ns spec-name)
                                                               :spec-name (.name field)))]
                                (update acc (optional-key? field context) conj spec-key)))
                            {}
                            record-fields)]
      (doseq [field record-fields]
        (to-spec! field (assoc context
                          :spec-ns (hierarchy-derive spec-ns spec-name))))
      (spec-def (assoc context
                  :spec-ns spec-ns
                  :spec-name spec-name)
                (record-spec-symbol (assoc context
                                      :spec-ns spec-ns
                                      :spec-name spec-name)
                                    spec-keys))
      (if (and (:spec-ns context)
               (:spec-name context))
        (do
          (spec-def context `~spec-keyword)
          (keyword (:spec-ns context) (:spec-name context)))
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
                    :missing-keys missing-keys})))
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
  "Recursively qualify keys of a map to match some schema namespace. Example dumb `explicit-union-types`:
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
