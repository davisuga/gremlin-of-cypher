(ns hello-world.handler
  (:import (org.opencypher.gremlin.translation TranslationFacade CypherAst))
  (:import (org.opencypher.gremlin.translation.translator TranslatorFlavor Translator))
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :refer [run-jetty]]
            [clojure.core.match :refer [match]]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]])
  (:gen-class))

(defn translate-tinker [query]
  (-> (new  TranslationFacade)
      (.toGremlinGroovy  query)))

(defn make-neptune-translator []
  (-> (Translator/builder)
      (.gremlinGroovy)
      (.inlineParameters)
      (.enableMultipleLabels)
      (.build (TranslatorFlavor/neptune))))

(defn make-cosmos-translator []
  (-> (Translator/builder)
      (.gremlinGroovy)
      (.build (TranslatorFlavor/cosmosDb))))

(defn translate-with-translator [translator query]
  (-> (CypherAst/parse query)
      (.buildTranslation translator)))

(defn translate [mode cypher]
  (match mode
    "cosmos" (translate-with-translator (make-cosmos-translator) cypher)
    "tinker" (translate-tinker cypher)
    "neptune" (translate-with-translator (make-neptune-translator) cypher)
    "janus" ""
    "neo4j" ""
    :else ""))

(defroutes app-routes
  (GET "/" request (translate (:mode (:params request)) (:cypher (:params request))))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults (wrap-cors app-routes :access-control-allow-origin [#"http://localhost:5173" #"https://gremlin-to-cypher-fe.vercel.app"]
                            :access-control-allow-methods [:get :put :post :delete]) site-defaults))

(defn -main [& args]
  (run-jetty app {:port (Integer/valueOf (or (System/getenv "PORT") "3000"))}))