(ns dove.core-test
  (:require [clojure.test :refer :all]
            [dove.core :as dove]
            [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen])
  (:import (dove PrimitiveTypes
                 EmptyRecord
                 ParentRecord
                 SomeEnum
                 Fixed8
                 Fixed16
                 ArrayRecord)
           (java.nio ByteBuffer)))

(deftest avro-float?
  (is (not (s/valid? dove/avro-float? Float/POSITIVE_INFINITY)))
  (is (not (s/valid? dove/avro-float? Float/NaN)))
  (is (not (s/valid? dove/avro-float? Float/NEGATIVE_INFINITY))))

(deftest avro-double?
  (is (not (s/valid? dove/avro-double? Double/POSITIVE_INFINITY)))
  (is (not (s/valid? dove/avro-double? Double/NaN)))
  (is (not (s/valid? dove/avro-double? Double/NEGATIVE_INFINITY))))

(deftest specification-test
  (reset! dove/ignored-specs #{})
  (testing "primitive types"
    (dove/to-spec! (PrimitiveTypes/getClassSchema) dove/convenient-args)
    (dotimes [_ 1e3]
      (let [sample (gen/generate (s/gen :dove/PrimitiveTypes))]
        (testing "null"
          (is (nil? (:aNull sample))))
        (testing "boolean"
          (is (boolean? (:aBoolean sample))))
        (testing "int"
          (is (int? (:aInt sample)))
          (is (s/valid? dove/avro-int? (:aInt sample))))
        (testing "long"
          (is (instance? Long (:aLong sample)))
          (is (s/valid? dove/avro-long? (:aLong sample))))
        (testing "float"
          (is (float? (:aFloat sample)))
          (is (s/valid? dove/avro-float? (:aFloat sample))))
        (testing "double"
          (is (double? (:aDouble sample)))
          (is (s/valid? dove/avro-double? (:aDouble sample))))
        (testing "bytes"
          (is (bytes? (:aBytes sample))))
        (testing "string"
          (is (string? (:aString sample)))))))
  (testing "complex types"
    (testing "records"
      (dove/to-spec! (EmptyRecord/getClassSchema) dove/convenient-args)
      (is (= {} (dissoc (gen/generate (s/gen :dove/EmptyRecord)) (:dove.spec/keyword dove/convenient-args))))
      (dove/to-spec! (ParentRecord/getClassSchema) dove/convenient-args)
      (is (contains? (gen/generate (s/gen :dove/ParentRecord)) :child))
      ;; recursive records can generate memory overflow
      )
    (testing "enums"
      (dove/to-spec! (SomeEnum/getClassSchema) {:enum-obj? true})
      (is (instance? SomeEnum (gen/generate (s/gen :dove/SomeEnum)))))
    (testing "arrays"
      (dove/to-spec! (ArrayRecord/getClassSchema) dove/convenient-args)
      (let [sample (gen/generate (s/gen :dove/ArrayRecord))]
        sample))
    (testing "maps"
      ;; Map keys are assumed to be strings.
      ;; an optional parameter (relax-map-str-keys? default: false) could relax and accept str-compatible keys.
      )
    (testing "unions")
    (testing "fixed"
      (dove/to-spec! (Fixed8/getClassSchema) dove/convenient-args)
      (let [fixed-8 (gen/generate (s/gen :dove/Fixed8))]
        (is (bytes? fixed-8))
        (is (= 8 (.limit (ByteBuffer/wrap fixed-8)))))
      (dove/to-spec! (Fixed16/getClassSchema) dove/convenient-args)
      (let [fixed-16 (gen/generate (s/gen :dove/Fixed16))]
        (is (bytes? fixed-16))
        (is (= 16 (.limit (ByteBuffer/wrap fixed-16)))))))
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
