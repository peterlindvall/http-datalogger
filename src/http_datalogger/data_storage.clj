(ns http-datalogger.data-storage
  (:require [clojure.data.json :refer [write-str read-json]]))

(defn save-data
  [data]
  (let [timestamp (str (java.util.Date.))]
    (spit "db.log"
          (str (write-str (assoc data :timestamp timestamp)) "\n")
          :append true)))

(defn get-data
  [{lines :lines}]
  (let [data (read-json (str "[" (slurp "db.log") "]"))]
    (if-not lines
      data
      (take-last lines data))))

(defn reset-data
  []
  (spit "db.log" ""))



(comment
  (get-data {:lines 3})
  (get-data {})

  )
