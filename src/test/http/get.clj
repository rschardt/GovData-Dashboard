(ns test.http.get
  (:require
   [clojure.test :refer [ deftest is testing ]]
   [http.get :as http-get]
  )
  (:gen-class))

(deftest transform_org_show_response_test
  (testing "Test if package_count field from json_response gets extracted")
  (let [
        input_json_response {"help" "https://ckan.govdata.de/api/3/action/help_show?name=organization_show", "success" true, "result" {"created" "2017-03-21T10:38:37.041473", "num_followers" 0, "approval_status" "approved", "id" "871d7e6b-82bd-4e3b-86bd-1256d0aab6e0", "name" "bam", "image_display_url" "", "display_name" "Bundesanstalt für Materialforschung und -prüfung (BAM)", "title" "Bundesanstalt für Materialforschung und -prüfung (BAM)", "type" "organization", "state" "active", "is_organization" true, "image_url" "", "description" "", "package_count" 5}}
        input_url "https://www.govdata.de/ckan/api/3/action/organization_show?id=bam&include_extras=false&include_users=false&include_groups=false&include_tags=false&include_followers=false"
        should_be 5
        ]
    (is
     (=
      (http-get/transform_org_show_response input_json_response input_url)
      should_be
      ))))

(deftest get_package_counts_of_organizations_test
  (testing "Test if list of conformed department names return their package_counts via org_show Action")
  (let [
        input_list (list "bam")
        should_be_list (list 5)
        ]
    (is
     (=
      (http-get/get_package_counts_of_organizations input_list)
      should_be_list
      ))))

(deftest transform_org_autocomplete_response_test
  (testing "Test if name field from json_response gets extracted")
  (let [
        input_json_response {"help" "https://ckan.govdata.de/api/3/action/help_show?name=organization_autocomplete", "success" true, "result" [{"id" "871d7e6b-82bd-4e3b-86bd-1256d0aab6e0", "name" "bam", "title" "Bundesanstalt für Materialforschung und -prüfung (BAM)"}]}
        input_url "https://www.govdata.de/ckan/api/3/action/organization_autocomplete?q=Bundesanstalt%20für%20Materialforschung%20und%20-prüfung%20(BAM)&limit=1"
        should_be "bam"
        ]
    (is
     (=
      (http-get/transform_org_autocomplete_response input_json_response input_url)
      should_be
      ))))

(deftest get_api_conform_department_names_test
  (testing "Test if list of department_names are getting conformed via org_autocomplete Action")
  (let [
        input (list "Bundesanstalt%20für%20Materialforschung%20und%20-prüfung%20(BAM)")
        should_be_list (list "bam")
        ]
    (is
     (=
      (http-get/get_api_conform_department_names input)
      should_be_list
      ))))
