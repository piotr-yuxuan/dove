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
                 ArrayRecord
                 MapRecord
                 CardSuit
                 UnionRecord)
           (java.nio ByteBuffer)))

(deftest avro-float?
  (is (not (s/valid? dove/avro-float? Float/POSITIVE_INFINITY)))
  (is (not (s/valid? dove/avro-float? Float/NaN)))
  (is (not (s/valid? dove/avro-float? Float/NEGATIVE_INFINITY))))

(deftest avro-double?
  (is (not (s/valid? dove/avro-double? Double/POSITIVE_INFINITY)))
  (is (not (s/valid? dove/avro-double? Double/NaN)))
  (is (not (s/valid? dove/avro-double? Double/NEGATIVE_INFINITY))))

(deftest avro-fixed?
  (let [n (rand-int 100)
        sample (gen/generate (s/gen (dove/->avro-fixed? n)))]
    (is (bytes? sample))
    (is (= n (.limit (ByteBuffer/wrap sample))))))

(deftest specification-test
  (reset! dove/ignored-specs #{})
  (dotimes [_ 1e2]
    (testing "primitive types"
      (dove/to-spec! (PrimitiveTypes/getClassSchema) dove/convenient-args)
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
          (is (string? (:aString sample))))))
    (testing "complex types"
      (testing "records"
        (dove/to-spec! (EmptyRecord/getClassSchema) {:hide-schema-name? true})
        (is (= {} (gen/generate (s/gen :dove/EmptyRecord))))
        (dove/to-spec! (ParentRecord/getClassSchema) dove/convenient-args)
        (is (contains? (gen/generate (s/gen :dove/ParentRecord)) :child))
        ;; recursive records can generate memory overflow
        )
      (testing "enums"
        (dove/to-spec! (SomeEnum/getClassSchema) {:enum-obj? true})
        (is (instance? SomeEnum (gen/generate (s/gen :dove/SomeEnum)))))
      (testing "arrays"
        (dove/to-spec! (ArrayRecord/getClassSchema) {:enum-obj? true :hide-schema-name? true})
        (let [sample (gen/generate (s/gen :dove/ArrayRecord))]
          (is (every? nil? (:arrayNull sample)))
          (is (every? boolean? (:arrayBoolean sample)))
          (is (every? #(s/valid? dove/avro-int? %) (:arrayInt sample)))
          (is (every? #(s/valid? dove/avro-long? %) (:arrayLong sample)))
          (is (every? #(s/valid? dove/avro-float? %) (:arrayFloat sample)))
          (is (every? #(s/valid? dove/avro-double? %) (:arrayDouble sample)))
          (is (every? #(s/valid? bytes? %) (:arrayBytes sample)))
          (is (every? #(s/valid? string? %) (:arrayString sample)))
          (is (every? #(instance? SomeEnum %) (:arraySomeEnum sample)))
          (is (every? #(= {} %) (:arrayEmptyRecord sample)))
          (is (every? #(s/valid? (dove/->avro-fixed? 8) %) (:arrayFixed8 sample)))))
      (testing "maps"
        ;; Map keys are assumed to be strings.
        ;; an optional parameter (str-keys? default: false) could relax and accept str-incompatible keys.
        (dove/to-spec! (MapRecord/getClassSchema) {:enum-obj? true :hide-schema-name? true})
        (let [sample (gen/generate (s/gen :dove/MapRecord))]
          (is (s/valid? (s/map-of string? nil?) (:mapNull (gen/generate (s/gen :dove/MapRecord)))))
          (is (s/valid? (s/map-of string? boolean?) (:mapBoolean (gen/generate (s/gen :dove/MapRecord)))))
          (is (s/valid? (s/map-of string? dove/avro-int?) (:mapInt sample)))
          (is (s/valid? (s/map-of string? dove/avro-long?) (:mapLong sample)))
          (is (s/valid? (s/map-of string? dove/avro-float?) (:mapFloat sample)))
          (is (s/valid? (s/map-of string? dove/avro-double?) (:mapDouble sample)))
          (is (s/valid? (s/map-of string? bytes?) (:mapBytes sample)))
          (is (s/valid? (s/map-of string? string?) (:mapString sample)))
          (is (s/valid? (s/map-of string? #(instance? SomeEnum %)) (:mapSomeEnum sample)))
          (is (s/valid? (s/map-of string? empty?) (:mapEmptyRecord sample)))
          (is (s/valid? (s/map-of string? (dove/->avro-fixed? 8)) (:mapFixed8 sample)))))
      (testing "unions"
        (dove/to-spec! (UnionRecord/getClassSchema) {:enum-obj? true})
        (let [sample (gen/generate (s/gen :dove/UnionRecord))]
          (is (s/valid? (s/or :nil? nil? :boolean? boolean?) (:nullBoolean sample)))
          (is (s/valid? (s/or :boolean? boolean? :int? int?) (:booleanInt sample)))
          (is (s/valid? (s/or :dove dove/avro-int? :bytes? bytes?) (:intBytes sample)))
          (is (s/valid? (s/or :string? string? :avro-long? dove/avro-long?) (:stringLong sample)))
          (is (s/valid? (s/or :someEnum? #(instance? SomeEnum %) :avro-double? dove/avro-double?) (:someEnumDouble sample)))
          (is (s/valid? (s/or :fixed8? (dove/->avro-fixed? 8) :avro-float? dove/avro-float?) (:fixed8Float sample)))
          (is (s/valid? (s/or :empty empty?
                              :dove/ParentRecord :dove/ParentRecord)
                        (:parentRecordEmptyRecord sample)))
          (is (s/valid? (s/or :null nil?
                              :someEnum #(instance? SomeEnum %)
                              :cardSuit #(instance? CardSuit %)
                              :fixed8 (dove/->avro-fixed? 8)
                              :fixed16 (dove/->avro-fixed? 16)
                              :int dove/avro-int?)
                        (:nullSomeEnumCardSuitFixed8Fixed16Int sample)))))
      (testing "fixed"
        (dove/to-spec! (Fixed8/getClassSchema) dove/convenient-args)
        (let [fixed-8 (gen/generate (s/gen :dove/Fixed8))]
          (s/valid? (dove/->avro-fixed? 8) fixed-8))
        (dove/to-spec! (Fixed16/getClassSchema) dove/convenient-args)
        (let [fixed-16 (gen/generate (s/gen :dove/Fixed16))]
          (s/valid? (dove/->avro-fixed? 16) fixed-16))))
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
        (testing "underlying fixed")))))

(deftest api-test
  (testing "")
  ;; case is left as is
  )
