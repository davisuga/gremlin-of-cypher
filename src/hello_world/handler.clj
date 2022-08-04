(ns hello-world.handler
  (:import (org.opencypher.gremlin.translation TranslationFacade CypherAst))
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn translate [mode cypher] (.toGremlinGroovy (new TranslationFacade)))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (POST "/to-gremlin" [request] translate)
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
