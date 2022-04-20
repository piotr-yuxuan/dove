(defproject com.github.piotr-yuxun/dove (-> "./resources/dove.version" slurp .trim)
  :description "Infer specs from SpecificRecord Java classes of any Avro named type (record, fixed, or enum)"
  :url "https://github.com/piotr-yuxuan/dove"
  :license {:name "GNU GPL v3+"
            :url "http://www.gnu.org/licenses/gpl-3.0.en.html"
            :distribution :repo}
  :pom-addition [:developers [:developer
                              [:name "胡雨軒 Петр"]
                              [:url "https://github.com/piotr-yuxuan"]]]
  :scm {:name "git"
        :url "https://github.com/piotr-yuxuan/dove"}
  :github/private? false
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [camel-snake-kebab "0.4.0"]
                 [org.clojure/clojure "1.10.0"]
                 [org.apache.avro/avro "1.8.2"]
                 [clj-time "0.15.1"]
                 [org.clojure/test.check "0.10.0-alpha3"]]
  :profiles {:github {:github/topics ["clojure" "avro" "spec" "avro-schema" "clojure-specs"
                                      "clojure-spec" "avro-format" "specific-schemas"]
                      :github/private? false}
             :provided {:dependencies []}
             :uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}
             :test {:plugins [[lein-shell "0.5.0"]]
                    :prep-tasks [["shell" "mvn" "clean" "compile"]]}
             :kaocha [:test]
             :pom {:dependencies [[org.apache.avro/avro-maven-plugin "1.8.2"]]}
             :dev {:global-vars {*warn-on-reflection* true}
                   :dependencies [[org.apache.avro/avro-maven-plugin "1.8.2"]
                                  [org.clojure/spec.alpha "0.2.176"]
                                  [danlentz/clj-uuid "0.1.7"]]
                   :source-paths ["test" "target/classes" "target/classes/dove"]}}
  :repositories [["packages.confluent.io" {:url "https://packages.confluent.io/maven/"}]
                 ["io.confluent" {:url "https://packages.confluent.io/maven/"}]]
  :deploy-repositories [["clojars" {:sign-releases false
                                    :url "https://clojars.org/repo"
                                    :username :env/WALTER_CLOJARS_USERNAME
                                    :password :env/WALTER_CLOJARS_PASSWORD}]
                        ["github" {:sign-releases false
                                   :url "https://maven.pkg.github.com/piotr-yuxuan/dove"
                                   :username :env/WALTER_ACTOR
                                   :password :env/WALTER_GITHUB_PASSWORD}]])
