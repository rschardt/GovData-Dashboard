(ns util.replace
  (:require
   [cheshire.core :refer :all]
  )
  (:gen-class))

(defn replace_whitespaces
  [department_list]
  (map
   (fn [department]
     (clojure.string/replace department #"\s+" "%20"))
   department_list))
