(ns test.http.server
  (:require
   [clojure.test :refer [ deftest is testing ]]
   [http.server :as http-server]
  )
  (:gen-class))

(deftest generate_html_test
  (testing "Test if html h6 gets generated correctly")
  (let [
        input_merged_map { "Bundesanstalt f\u00fcr Materialforschung und -pr\u00fcfung (BAM)" 5 }
        should_be (list "<h6>Bundesanstalt für Materialforschung und -prüfung (BAM): 5</h6>")
        ]
    (is
     (=
      (http-server/generate_html input_merged_map)
      should_be
      ))))

(deftest generate_http_body_test
  (testing "Test if http body gets generated correctly")
  (let [
        should_be (list "<h6>Bundesanstalt für Arbeitsschutz und Arbeitsmedizin : 15</h6>" "<h6>Bundesamt für Wirtschaft und Ausfuhrkontrolle: 5</h6>" "<h6>Bundesministerium der Verteidigung: 4</h6>" "<h6>Bundesamt für Verbraucherschutz und Lebensmittelsicherheit: 18</h6>" "<h6>Bundesministerium der Finanzen: 58</h6>" "<h6>Deutsches Patent- und Markenamt: 18</h6>" "<h6>Bundesministerium für Familie, Senioren, Frauen und Jugend: 71</h6>" "<h6>Bundesministerium für Wirtschaft und Klimaschutz: 23</h6>" "<h6>Bundesministerium für Bildung und Forschung: 321</h6>" "<h6>Bundesamt für Justiz: 771</h6>" "<h6>Bundesministerium für Gesundheit: 0</h6>" "<h6>Generalzolldirektion: 7</h6>" "<h6>Bundesministerium der Justiz: 0</h6>" "<h6>Bundesinstitut für Bau-, Stadt- und Raumforschung (BBSR) im Bundesamt für Bauwesen und Raumordnung (BBR): 9</h6>" "<h6>Bundeszentralamt für Steuern: 2</h6>" "<h6>Auswärtiges Amt: 7</h6>" "<h6>Bundesausgleichsamt: 2</h6>" "<h6>mCLOUD: 4763</h6>" "<h6>Bundesministerium für Arbeit und Soziales: 77</h6>" "<h6>Bundesverwaltungsamt: 2</h6>" "<h6>Statistisches Bundesamt: 2916</h6>" "<h6>Bundesministerium des Innern und Heimat: 1077</h6>" "<h6>Bundesministerium für Digitales und Verkehr: 0</h6>" "<h6>Bundessortenamt: 2</h6>" "<h6>Bundesministerium für wirtschaftliche Zusammenarbeit und Entwicklung: 3</h6>" "<h6>Max Rubner-Institut: 2</h6>" "<h6>Bundesanstalt für Materialforschung und -prüfung (BAM): 5</h6>" "<h6>Bundesamt für Soziale Sicherung: 0</h6>" "<h6>Bundesministerium für Ernährung und Landwirtschaft: 26</h6>" "<h6>ITZ-Bund: 0</h6>")
        ]
    (is
     (=
      (http-server/generate_http_body)
      should_be
      ))))

(deftest app_test
  (testing "Test if correct http response is returned")
  (let [
        input_req ""
        should_be {:status 200, :headers {"Content-Type" "text/html"}, :body nil}
        ]
    (is
     (=
      (do
        (reset! http-server/HTTP_BODY nil)
        (http-server/app input_req)
        )
      should_be
      ))))

(deftest run_test
  (testing "Test if http-server is started and the correct response returned")
  (let [
        should_be "<h6>Bundesanstalt für Arbeitsschutz und Arbeitsmedizin : 15</h6><h6>Bundesamt für Wirtschaft und Ausfuhrkontrolle: 5</h6><h6>Bundesministerium der Verteidigung: 4</h6><h6>Bundesamt für Verbraucherschutz und Lebensmittelsicherheit: 18</h6><h6>Bundesministerium der Finanzen: 58</h6><h6>Deutsches Patent- und Markenamt: 18</h6><h6>Bundesministerium für Familie, Senioren, Frauen und Jugend: 71</h6><h6>Bundesministerium für Wirtschaft und Klimaschutz: 23</h6><h6>Bundesministerium für Bildung und Forschung: 321</h6><h6>Bundesamt für Justiz: 771</h6><h6>Bundesministerium für Gesundheit: 0</h6><h6>Generalzolldirektion: 7</h6><h6>Bundesministerium der Justiz: 0</h6><h6>Bundesinstitut für Bau-, Stadt- und Raumforschung (BBSR) im Bundesamt für Bauwesen und Raumordnung (BBR): 9</h6><h6>Bundeszentralamt für Steuern: 2</h6><h6>Auswärtiges Amt: 7</h6><h6>Bundesausgleichsamt: 2</h6><h6>mCLOUD: 4763</h6><h6>Bundesministerium für Arbeit und Soziales: 77</h6><h6>Bundesverwaltungsamt: 2</h6><h6>Statistisches Bundesamt: 2916</h6><h6>Bundesministerium des Innern und Heimat: 1077</h6><h6>Bundesministerium für Digitales und Verkehr: 0</h6><h6>Bundessortenamt: 2</h6><h6>Bundesministerium für wirtschaftliche Zusammenarbeit und Entwicklung: 3</h6><h6>Max Rubner-Institut: 2</h6><h6>Bundesanstalt für Materialforschung und -prüfung (BAM): 5</h6><h6>Bundesamt für Soziale Sicherung: 0</h6><h6>Bundesministerium für Ernährung und Landwirtschaft: 26</h6><h6>ITZ-Bund: 0</h6>"
        ]
    (is
     (=
      (do
        (let [ shutdown (http-server/run) ]
          (Thread/sleep 100)
          (let [ response (slurp "http://localhost:8080") ]
            (shutdown)
            response)))
      should_be
      ))))
