(ns dove.core-impl
  "Avro schema are constant, spec are designed to be constant. As a result, side effects are acceptable in protocol ToSpec"
  (:require [clojure.spec.alpha :as s]
            [clojure.test.check.generators :as test.g])
  (:import (org.apache.avro Schema$Field Schema$EnumSchema Schema$NullSchema Schema$BooleanSchema Schema$DoubleSchema Schema$FloatSchema Schema$LongSchema Schema$IntSchema Schema$BytesSchema Schema$StringSchema Schema$FixedSchema Schema$RecordSchema Schema$UnionSchema Schema$MapSchema Schema$ArraySchema)))

(defprotocol ToSpec
  (to-spec! [this context]))

(def avro-fixed?
  (memoize
    (fn [fixed-size]
      "Sequence of 8-bit unsigned bytes"
      (s/with-gen
        bytes?
        #(test.g/fmap byte-array (test.g/vector test.g/byte fixed-size))))))

(def avro-int?
  "Int: 32-bit signed two's complement integer"
  int?)

(def avro-long?
  "Long: 64-bit signed integer"
  (s/with-gen
    #(instance? Long %)
    #(test.g/fmap
       long
       (test.g/double* {:NaN? false
                        :min Long/MIN_VALUE
                        :max Long/MAX_VALUE}))))

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

(defn- derive-name
  [parent descendant]
  (str parent "." descendant))

(def spec-def
  (memoize
    (fn [context spec-symbol]
      (eval `(s/def
               ~(keyword (:spec-ns context) (:spec-name context))
               ~(eval spec-symbol))))))

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
    (spec-def context `avro-int?)
    (keyword (:spec-ns context) (:spec-name context)))

  Schema$LongSchema
  (to-spec! [this context]
    (spec-def context `avro-long?)
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
    (let [spec-keyword (keyword (.getNamespace this)
                                (.getName this))
          spec-values (set (.getEnumSymbols this))]
      (spec-def (assoc context
                  :spec-ns (.getNamespace this)
                  :spec-name (.getName this))
                spec-values)
      (spec-def context `~spec-keyword)
      (keyword (:spec-ns context) (:spec-name context))))

  Schema$FixedSchema
  (to-spec! [this context]
    (let [spec-keyword (keyword (.getNamespace this)
                                (.getName this))]
      (spec-def (assoc context
                  :spec-ns (.getNamespace this)
                  :spec-name (.getName this))
                `~(avro-fixed? (.getFixedSize this)))
      (spec-def context
                `~spec-keyword)
      (keyword (:spec-ns context) (:spec-name context))))

  Schema$MapSchema
  (to-spec! [this context]
    (let [value-schema (.getValueType this)
          spec-name (derive-name (:spec-name context) (.getName value-schema))
          spec-keyword (to-spec! value-schema (assoc context :spec-name spec-name))]
      (spec-def context
                `~(s/map-of string? spec-keyword))
      (keyword (:spec-ns context) (:spec-name context))))

  Schema$ArraySchema
  (to-spec! [this context]
    (let [value-schema (.getElementType this)
          spec-name (derive-name (:spec-name context) (.getName value-schema))
          spec-keyword (to-spec! value-schema (assoc context :spec-name spec-name))]
      (spec-def context
                `~(s/map-of string? spec-keyword))
      (keyword (:spec-ns context) (:spec-name context))))

  Schema$UnionSchema
  (to-spec! [this context]
    (let [spec-names (->> (.getTypes ^Schema$UnionSchema this)
                          (map (fn [schema]
                                 (let [spec-name (derive-name (:spec-name context) (.getName schema))]
                                   (to-spec! schema (assoc context :spec-name spec-name))))))]
      (spec-def context
                `(s/or
                   ~@(mapcat
                       (juxt identity
                             identity)
                       spec-names)))
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
          spec-keys (map (fn [field]
                           (to-spec! field (assoc context
                                             :spec-ns (derive-name spec-ns spec-name)
                                             :spec-name (.name field))))
                         record-fields)]
      (doseq [field record-fields]
        (to-spec! field (assoc context
                          :spec-ns (derive-name spec-ns spec-name))))
      (spec-def (assoc context
                  :spec-ns spec-ns
                  :spec-name spec-name)
                `(s/keys :req ~spec-keys))
      (if (and (:spec-ns context)
               (:spec-name context))
        (do
          (spec-def context `~spec-keyword)
          (keyword (:spec-ns context) (:spec-name context)))
        spec-keyword))))
