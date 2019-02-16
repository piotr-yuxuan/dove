(ns dove.core-impl
  "Avro schema are constant, spec are designed to be constant. As a result, side effects are acceptable in protocol ToSpec"
  (:require [clojure.spec.alpha :as s]
            [clojure.test.check.generators :as test.g])
  (:import (org.apache.avro Schema$Field Schema$EnumSchema Schema$NullSchema Schema$BooleanSchema Schema$DoubleSchema Schema$FloatSchema Schema$LongSchema Schema$IntSchema Schema$BytesSchema Schema$StringSchema Schema$FixedSchema Schema$RecordSchema Schema$UnionSchema Schema$MapSchema Schema$ArraySchema)))

(defprotocol ToSpec
  (to-spec! [this context]))

(def spec-long?
  (s/with-gen
    #(instance? Long %)
    #(test.g/fmap
       long
       (test.g/double* {:infinite? false
                        :NaN? false
                        :min Long/MIN_VALUE
                        :max Long/MAX_VALUE}))))

(def byte-min (- (Math/pow 2 16)))
(def byte-max (dec (Math/pow 2 16)))

(def spec-byte? ;; dubious
  (s/with-gen
    int?
    #(test.g/fmap
      int
      (test.g/double* {:min byte-min
                       :max byte-max}))))

(def spec-def (memoize eval)) ;; If you're unhappy with that, show me another way.

(defn- derive-name
  [parent descendant]
  (str parent "." descendant))

(def field-spec-keyword
  (memoize
    (fn [context]
      (keyword (:spec-ns context)
               (:spec-name context)))))

(extend-protocol ToSpec
  Schema$StringSchema
  (to-spec! [this context]
    (spec-def `(s/def
                 ~(field-spec-keyword context)
                 string?))
    (field-spec-keyword context))

  Schema$BytesSchema
  (to-spec! [this context]
    (spec-def `(s/def
                 ~(field-spec-keyword context)
                 bytes?))
    (field-spec-keyword context))

  Schema$IntSchema
  (to-spec! [this context]
    (spec-def `(s/def
                 ~(field-spec-keyword context)
                 int?))
    (field-spec-keyword context))

  Schema$LongSchema
  (to-spec! [this context]
    (spec-def `(s/def
                 ~(field-spec-keyword context)
                 spec-long?))
    (field-spec-keyword context))

  Schema$FloatSchema
  (to-spec! [this context]
    (spec-def `(s/def
                 ~(field-spec-keyword context)
                 float?))
    (field-spec-keyword context))

  Schema$DoubleSchema
  (to-spec! [this context]
    (spec-def `(s/def
                 ~(field-spec-keyword context)
                 double?))
    (field-spec-keyword context))

  Schema$BooleanSchema
  (to-spec! [this context]
    (spec-def `(s/def
                 ~(field-spec-keyword context)
                 boolean?))
    (field-spec-keyword context))

  Schema$NullSchema
  (to-spec! [this context]
    (spec-def `(s/def
                 ~(field-spec-keyword context)
                 nil?))
    (field-spec-keyword context))

  Schema$EnumSchema
  (to-spec! [this context]
    (let [spec-keyword (keyword (.getNamespace this)
                                (.getName this))
          spec-values (set (.getEnumSymbols this))]
      (spec-def `(s/def
                   ~spec-keyword
                   ~spec-values))
      (spec-def `(s/def
                   ~(field-spec-keyword context)
                   ~spec-keyword))
      (field-spec-keyword context)))

  Schema$FixedSchema
  (to-spec! [this context]
    (let [spec-keyword (keyword (.getNamespace this)
                                (.getName this))]
      (spec-def `(s/def
                   ~spec-keyword
                   (s/coll-of spec-byte?
                              :count ~(.getFixedSize this))))
      (spec-def `(s/def
                   ~(field-spec-keyword context)
                   ~spec-keyword))
      (field-spec-keyword context)))

  Schema$MapSchema
  (to-spec! [this context]
    (let [spec-keyword (let [value-schema (.getValueType this)
                             spec-name (derive-name (:spec-name context)
                                                    (.getName value-schema))]
                         (to-spec! value-schema (assoc context
                                                       :spec-name spec-name)))]
      (spec-def `(s/def
                   ~(field-spec-keyword context)
                   (s/map-of string?
                             ~spec-keyword)))
      (field-spec-keyword context)))

  Schema$ArraySchema
  (to-spec! [this context]
    (let [spec-keyword (let [value-schema (.getElementType this)
                             spec-name (derive-name (:spec-name context)
                                                    (.getName value-schema))]
                         (to-spec! value-schema (assoc context
                                                       :spec-name spec-name)))]
      (spec-def `(s/def
                   ~(field-spec-keyword context)
                   (s/map-of string?
                             ~spec-keyword)))
      (field-spec-keyword context)))

  Schema$UnionSchema
  (to-spec! [this context]
    (let [spec-names (->> (.getTypes ^Schema$UnionSchema this)
                          (map (fn [schema]
                                 (let [spec-name (derive-name (:spec-name context)
                                                              (.getName schema))]
                                   (to-spec! schema (assoc context
                                                           :spec-name spec-name))))))]
      (spec-def `(s/def
                   ~(field-spec-keyword context)
                   (s/or ~@(mapcat (juxt identity
                                         identity)
                                   spec-names))))
      (field-spec-keyword context)))

  Schema$Field
  (to-spec! [this context]
    (to-spec! (.schema this)
              (assoc context
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
      (spec-def `(s/def
                   ~spec-keyword
                   (s/keys :req ~spec-keys)))
      (if (and (:spec-ns context)
               (:spec-name context))
        (do
          (spec-def `(s/def
                       ~(field-spec-keyword context)
                       ~spec-keyword))
          (field-spec-keyword context))
        spec-keyword))))
