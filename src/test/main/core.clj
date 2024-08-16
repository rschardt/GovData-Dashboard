(ns test.main.core
  (:require
   [clojure.test :refer [ deftest is testing ]]
   [test.util.replace]
   [test.util.parse]
   [test.http.get]
   [test.http.server]
  )
  (:gen-class))

(defn -main
  [& args]
  (clojure.test/run-all-tests)
  )
