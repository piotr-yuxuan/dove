(defproject com.github.piotr-yuxuan/dove (-> "./resources/dove.version" slurp .trim)
  :description "Recursively infer clojure spec from any (nested) org.apache.avro.Schema"
  :url "https://github.com/piotr-yuxuan/dove"
  :license {:name "European Union Public License 1.2 or later"
            :url "https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12"
            :distribution :repo}
  :scm {:name "git"
        :url "https://github.com/piotr-yuxuan/dove"}
  :pom-addition [:developers [:developer
                              [:name "胡雨軒 Петр"]
                              [:url "https://github.com/piotr-yuxuan"]]]
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [camel-snake-kebab "0.4.0"]
                 [org.clojure/clojure "1.10.0"]
                 [org.apache.avro/avro "1.8.2"]
                 [clj-time "0.15.1"]]
  :main piotr-yuxuan.walter-ci.main
  :profiles {:github {:github/topics ["clojure" "avro" "spec" "avro-schema"
                                      "clojure-specs" "clojure-spec" "avro-format"]
                      :github/private? false}
             :provided {:dependencies [[org.clojure/clojure "1.10.3"]]}
             :dev {:global-vars {*warn-on-reflection* true}
                   :plugins [[lein-shell "0.5.0"]]
                   :prep-tasks [["shell" "mvn" "clean" "compile"]]
                   :dependencies [[org.apache.avro/avro-maven-plugin "1.8.2"]
                                  [org.clojure/test.check "0.10.0-alpha3"]
                                  [org.clojure/spec.alpha "0.2.176"]
                                  [danlentz/clj-uuid "0.1.7"]]}
             :uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
