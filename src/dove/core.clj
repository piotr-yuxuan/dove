(ns dove.core
  (:require [clojure.walk :as walk]
            [clojure.spec.alpha :as s]
            [clj-uuid :as uuid]
            [clojure.spec.gen.alpha :as g])
  (:import (org.apache.avro Schema)
           (java.nio ByteBuffer))
  (:use dove.core-impl)
  (:gen-class))

(defn- bytes-to-uuid
  [bytes]
  (let [buffer ^ByteBuffer (ByteBuffer/wrap bytes)]
    (uuid/v4 (.getLong buffer) (.getLong buffer))))

(def generator-hooks
  (atom
    {:some.package/UID bytes-to-uuid}))

(defn infer-spec!
  "Recursively infer and register spec of record-schema and any nested
  schemas."
  [^Schema record-schema]
  (to-spec! record-schema {:generator-hooks generator-hooks}))

(defn un-ns-kw-keys
  "Recursively transforms all map keys from namespaced keywords to
  keywords without namespace."
  [m]
  (let [f (fn [[k v]] (if (keyword? k) [(keyword (name k)) v] [k v]))]
    (walk/postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) m)))
