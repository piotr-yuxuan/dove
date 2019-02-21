(ns dove.core
  (:require [clojure.walk :as walk]
            [dove.core-impl :as impl])
  (:import (java.nio ByteBuffer))
  (:gen-class))

(def to-spec! impl/to-spec!)



(defn f-keys
  "Recursively transforms all map keys from namespaced keywords to
  keywords without namespace."
  [f m]
  (let [g (fn [[k v]] (if (keyword? k) [(f k) v] [k v]))]
    (walk/postwalk (fn [x] (if (map? x) (into {} (map g x)) x)) m)))
