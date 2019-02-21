(ns dove.core
  (:require [clojure.walk :as walk]
            [clojure.spec.alpha :as s]
            [clj-uuid :as uuid]
            [clojure.spec.gen.alpha :as g]
            [clojure.test.check.generators :as test.g]
            [inet.data.ip :as ip])
  (:import (org.apache.avro Schema)
           (java.nio ByteBuffer)
           (some.package SomeAvroSchema))
  (:use dove.core-impl)
  (:gen-class))

(defn- bytes-to-uuid
  [bytes]
  (let [buffer ^ByteBuffer (ByteBuffer/wrap bytes)]
    (uuid/v4 (.getLong buffer) (.getLong buffer))))



(defn f-keys
  "Recursively transforms all map keys from namespaced keywords to
  keywords without namespace."
  [f m]
  (let [g (fn [[k v]] (if (keyword? k) [(f k) v] [k v]))]
    (walk/postwalk (fn [x] (if (map? x) (into {} (map g x)) x)) m)))

(to-spec! (SomeAvroSchema/getClassSchema) {:dry-run? false})

(s/def :some.package/UID
  (s/with-gen
    uuid?
    #(test.g/fmap bytes-to-uuid (s/gen (->avro-fixed? 16)))))

(->> :some.package/UID
     s/gen
     g/generate)

(s/def :some.package/IPv4
  (s/with-gen
    ip/address?
    #(test.g/fmap ip/address (s/gen (->avro-fixed? 4)))))

(->> :some.package/IPv4
     s/gen
     g/generate)

(s/def :some.package/IPv6
  (s/with-gen
    ip/address?
    #(test.g/fmap ip/address (s/gen (->avro-fixed? 16)))))

(->> :some.package/IPv6
     s/gen
     g/generate
     (f-keys (comp keyword name)))
