(ns http.get
  (:require
    [cheshire.core :refer :all]
  )
  (:gen-class))

(defn transform_org_show_response
  [json_response url]
  (if (= json_response 0 )
    0
    (if (Boolean/valueOf (get json_response "success"))
      (do
        ;;(println (format "Success for: %s" url))
        (let [result (get json_response "result")]
          (get result "package_count")))
      (println (format "Fail for: %s" url)))))

(defn get_package_counts_of_organizations
  [organization_ids]
  (map
   (fn [org_id]
     (if (= org_id 0)
       org_id
       (let [ url (format "https://www.govdata.de/ckan/api/3/action/organization_show?id=%s&include_extras=false&include_users=false&include_groups=false&include_tags=false&include_followers=false" org_id) ]
         ;;(println (format "Get Request for: %s" url))
         (Thread/sleep 5)
         (transform_org_show_response
          (parse-string
           (slurp url))
          url))))
   organization_ids))

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
