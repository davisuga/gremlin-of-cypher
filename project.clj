(defproject hello-world "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [org.clojure/core.match "1.0.0"]
                 [org.opencypher.gremlin/translation "1.0.4"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [ring/ring-defaults "0.3.2"]]
  :plugins [[lein-ring "0.12.5"] [lein-typed "0.4.6"]]
  :ring {:handler routes/app}
  :profiles
  {:uberjar {:aot :all
             :main hello-world.handler}
   :dev {:dependencies [[org.clojure.typed/checker.jvm "1.0.1"]
                        [org.clojure.typed/annotator.jvm "1.0.1"]
                        [javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})

