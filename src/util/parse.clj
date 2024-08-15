(ns util.parse
  (:require
   [cheshire.core :refer :all]
  )
  (:gen-class))

(defn get_department_entry
  [outer_entry]
  (if (contains? outer_entry "subordinates")
    (concat [ (get outer_entry "name") ]
      (map
       (fn [inner_entry] (get inner_entry "name"))
       (get outer_entry "subordinates")))
     [ (get outer_entry "name") ]))

(defn parse_departments
  []
  (mapcat
   get_department_entry
   (get
    (parse-stream
     (clojure.java.io/reader "departments.json"))
    "departments")))
