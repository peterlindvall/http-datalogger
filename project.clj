(defproject http-datalogger "0.1.0-SNAPSHOT"
  :description "Web server for receiving data from IoT devices"
  :url ""
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v20.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [http-kit "2.3.0"]
                 [org.clojure/data.json "1.0.0"]
                 ]

  :main ^:skip-aot http-datalogger.main

  :profiles {:uberjar {:aot :all}}
  )
