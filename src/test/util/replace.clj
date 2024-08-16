(ns test.util.replace
  (:require
   [clojure.test :refer [ deftest is testing ]]
   [util.replace :as util-replace]
  )
  (:gen-class))

(deftest replace_whitespaces_test
  (testing "Test if whitespaces in list of strings are replaced")
  (let [
        original_list (list "" " " " a " "a a")
        should_be_list (list "" "%20" "%20a%20" "a%20a")
        ]
    (is
     (=
      (util-replace/replace_whitespaces original_list)
      should_be_list
      ))))
