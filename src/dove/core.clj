(ns dove.core
  (:require [clojure.walk :as walk])
  (:import (org.apache.avro Schema))
  (:use dove.core-impl)
  (:gen-class))

(defn infer-spec!
  "Recursively infer and register spec of record-schema and any nested
  schemas."
  [^Schema record-schema]
  (to-spec! record-schema {}))

(defn un-ns-kw-keys
  "Recursively transforms all map keys from namespaced keywords to
  keywords without namespace."
  [m]
  (let [f (fn [[k v]] (if (keyword? k) [(keyword (name k)) v] [k v]))]
    (walk/postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) m)))
