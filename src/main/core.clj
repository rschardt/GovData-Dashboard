(ns main.core
  (:require
   [http.server :as http-server]
   )
  (:gen-class))

(defn -main
  [& args]
  (println (str "GovData-Dashboard has started on port: 8080"))
  (http-server/run))
