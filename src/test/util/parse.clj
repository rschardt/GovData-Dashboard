(ns test.util.parse
  (:require
   [clojure.test :refer [ deftest is testing ]]
   [util.parse :as util-parse]
  )
  (:gen-class))

(deftest get_department_entry_test
  (testing "Test if subordinates are flatmapped")
  (let [
        original_map { "name" "Bundesministerium der Justiz", "subordinates" [{"name" "Deutsches Patent- und Markenamt"} {"name" "Bundesamt für Justiz"}]}
        should_be_list (list "Bundesministerium der Justiz" "Deutsches Patent- und Markenamt" "Bundesamt für Justiz")
        ]
    (is
     (=
      (util-parse/get_department_entry original_map)
      should_be_list
      ))))

(deftest parse_departments_test
  (testing "Test if departments.json gets parsed correctly")
  (let [
        should_be_list (list "Auswärtiges Amt" "Bundesministerium der Justiz" "Deutsches Patent- und Markenamt" "Bundesamt für Justiz" "Bundesministerium der Finanzen" "Bundeszentralamt für Steuern" "Generalzolldirektion" "ITZ-Bund" "Bundesministerium der Verteidigung" "Bundesministerium des Innern und Heimat" "Bundesinstitut für Bau-, Stadt- und Raumforschung (BBSR) im Bundesamt für Bauwesen und Raumordnung (BBR)" "Bundesausgleichsamt" "Bundesverwaltungsamt" "Statistisches Bundesamt" "Bundesministerium für Arbeit und Soziales" "Bundesanstalt für Arbeitsschutz und Arbeitsmedizin " "Bundesministerium für Bildung und Forschung" "Bundesministerium für Familie, Senioren, Frauen und Jugend" "Bundesministerium für wirtschaftliche Zusammenarbeit und Entwicklung" "Bundesministerium für Wirtschaft und Klimaschutz" "Bundesamt für Wirtschaft und Ausfuhrkontrolle" "Bundesanstalt für Materialforschung und -prüfung (BAM)" "Bundesministerium für Ernährung und Landwirtschaft" "Bundesamt für Verbraucherschutz und Lebensmittelsicherheit" "Bundessortenamt" "Max Rubner-Institut" "Bundesministerium für Gesundheit" "Bundesamt für Soziale Sicherung" "Bundesministerium für Digitales und Verkehr" "mCLOUD")
        ]
    (is
     (=
      (util-parse/parse_departments)
      should_be_list
      ))))
