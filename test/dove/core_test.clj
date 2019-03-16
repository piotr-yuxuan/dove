(ns dove.core-test
  (:require [clojure.test :refer :all]
            [dove.core :as dove])
  (:use [potemkin.collections])
  (:import (dove IPv4)))

;; (dove/to-spec! (IPv4/getClassSchema) dove/convenient-args)

(def-map-type
  RecordMap [m mta]
  (get [_ k default-value]
       (if (contains? m k)
         (let [v (get m k)]
           (if (instance? clojure.lang.Delay v)
             @v
             v))
         default-value))
  (assoc [_ k v]
         (RecordMap. (assoc m k v) mta))
  (dissoc [_ k]
          (RecordMap. (dissoc m k) mta))
  (keys [_]
        (keys m))
  (meta [_]
        mta)
  (with-meta [_ mta]
    (RecordMap. m mta)))

(def s (->RecordMap {:a 1 :b 2 :c 3} nil))
(type (into {} s))

(deftest specification-test
  (testing "primitive types"
    (testing "null")
    (testing "boolean")
    (testing "int")
    (testing "long")
    (testing "float")
    (testing "double"
      ;; in sich types, check infinity isn't a valid value
      )
    (testing "bytes")
    (testing "string"
      ;; unicode character sequence
      ))
  (testing "complex types"
    (testing "records"
      ;; test empty record schema generates empty map
      ;; nested records
      )
    (testing "enums")
    (testing "arrays")
    (testing "maps"
      ;; Map keys are assumed to be strings.
      ;; an optional parameter (relax-map-str-keys? default: false) could relax and accept str-compatible keys.
      )
    (testing "unions")
    (testing "fixed")
    ;; size
    )
  (testing "named types"
    ;; checking types have correct kw.
    (testing "record")
    (testing "enums")
    (testing "fixed"))
  (testing "aliases"
    ;; use the actual name
    ;; accept alias record names
    ;; accept alias field names
    ;; accept alias enum names
    ;; accept alias fixed names
    )
  (testing "logical types"
    (testing "decimal"
      (testing "underlying bytes")
      (testing "underlying fixed"))
    (testing "date"
      (testing "underlying int"))
    (testing "time-millis"
      (testing "underlying int"))
    (testing "time-micros"
      (testing "underlying long"))
    (testing "timestamp-millis"
      (testing "underlying long"))
    (testing "timestamp-micros"
      (testing "underlying long"))
    (testing "duration"
      (testing "underlying fixed"))))

(deftest api-test
  (testing "")
  )
