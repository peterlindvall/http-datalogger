(ns http-datalogger.endpoints
  (:require [org.httpkit.client :refer [request]]
            [clojure.data.json :refer [write-str read-json]]
            [http-datalogger.data-storage :refer [save-data get-data reset-data]]))


(defn create-response [data]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (write-str data)}
  )

(defn handle-request [request]
  (let [uri (:uri request)]

    (cond (= uri "/add-data")
          (let [data (read-json (slurp (:body request)))]
            (-> (save-data data)
                (create-response)))

          (= uri "/get-data")
          (let [data (read-json (slurp (:body request)))]
            (-> (get-data data)
                (create-response)))

          (= uri "/reset-data")
          (reset-data)

          :else
          (create-response "Nothing on this url"))))






(comment

  (deref (request {:url    "http://localhost:8080/add-data"
                   :method :post
                   :body   (write-str {:temp 10 :humidity 80 :location :north})}))

  (-> (request {:url    "http://localhost:8080/get-data"
                :method :post
                :body   (write-str {})})
      (deref)
      (:body)
      (read-json))

  (-> (request {:url    "http://localhost:8080/get-data"
                :method :post
                :body   (write-str {:lines 2})})
      (deref)
      (:body)
      (read-json))

  )