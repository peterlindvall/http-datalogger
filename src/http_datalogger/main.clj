(ns http-datalogger.main
  (:require [org.httpkit.server :refer [run-server]]
            [http-datalogger.endpoints :refer [handle-request]])
  (:gen-class))

(defonce server (atom nil))

(defn stop! []
  (when-not (nil? (deref server))
    ((deref server) :timeout 100)
    (reset! server nil)))

(defn start! []
  (reset! server (run-server (fn [request]
                               (handle-request request))
                             {:port 4711})))

(defn -main [& _]
  (start!))


(comment
  (start!)
  (stop!)

  )
