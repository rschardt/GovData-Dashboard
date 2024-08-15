(ns http.server
  (:require
   [org.httpkit.server :as hk-server]
   [hiccup2.core :as h]
   [http.get :as http-get]
   [util.parse :as util-parse]
   [util.replace :as util-replace]
  )
  (:gen-class))

(def HTTP_BODY (atom nil))

(defn generate_html
  [merged_map]
  (map
   (fn [item]
     (str (h/html [:h6 (format "%1$s: %2$s" (key item) (val item))])))
   merged_map))

(defn generate_http_body
  []
  (let [department_list (util-parse/parse_departments)]
    (let [whitespace_replaced_department_list (util-replace/replace_whitespaces department_list)]
      (let [conform_department_list (http-get/get_api_conform_department_names whitespace_replaced_department_list)]
        (let [organization_package_counts (http-get/get_package_counts_of_organizations conform_department_list)]
          (generate_html (zipmap department_list organization_package_counts)))))))

(defn app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body
   (deref HTTP_BODY)
   })

(defn run
  []
  (reset! HTTP_BODY (generate_http_body))
  (hk-server/run-server app {:port 8080}))
