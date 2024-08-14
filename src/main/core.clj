(ns main.core
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

(defn -main
  [& args]
  (println (str "GovData-Dashboard has started!"))
  (println)
  (let [department_list (parse_departments)]
    (println (replace_whitespaces department_list)))
  (println)
  (println (str "GovData-Dashboard has shutdown!")))
