(ns main.core
  (:require
    [cheshire.core :refer :all]
  )
  (:gen-class))

(defn transform_org_autocomplete_response
  [json_response url]
  (if (Boolean/valueOf (get json_response "success"))
    (do
      ;;(println (format "Success for: %s" url))
      (let [result (get json_response "result")]
        (if (>= (count result) 1)
        (get
         (nth
          result
          0)
         "name")
        0)))
    (println (format "Fail for: %s" url))))

(defn get_api_conform_department_names
  [department_list]
  (map
   (fn [department]
     (let [ url (format "https://www.govdata.de/ckan/api/3/action/organization_autocomplete?q=%s&limit=1" department) ]
       ;; (println (format "Get Request for: %s" url))
       (Thread/sleep 5)
       (transform_org_autocomplete_response
        (parse-string
         (slurp url))
        url)))
   department_list))

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
    (let [whitespace_replaced_department_list (replace_whitespaces department_list)]
      (println (get_api_conform_department_names whitespace_replaced_department_list))))
  (println)
  (println (str "GovData-Dashboard has shutdown!")))
