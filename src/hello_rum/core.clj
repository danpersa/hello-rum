(ns hello-rum.core
  (:gen-class)
  (require [rum.core :as rum]
           [immutant.web :as web]
           [ring.middleware.resource :as ring-res]
           [ring.middleware.file :as ring-file]
           [hello-rum.components :as comp]))

(rum/defc my-comp [s] [:div s])

(def html
  (str "<html>
  <head>
    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/tasks.css\">
    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/material.min.inc.css\">
  </head>
  <body>
    <div>Hello To The Page</div>
    <div id='my-hello-component'>"
       (rum/render-html (comp/my-comp "hello"))
       "</div>"
       "<div class=example>
          <div class=example-title>Local state</div>
       <div id=local-state>"
         (rum/render-html (comp/local-state "Clicks count")) "</div>
       </div>"
       "<script src='app/js/main.js' type='text/javascript'></script>
  </body>
</html>"))

(defn app [request]
  {:status 200
   :body html})

(def app-with-static (ring-res/wrap-resource app "public"))
; (def app-with-static-files (ring-file/wrap-file app "/Users/dpersa/Prog/clojure/hello-rum/target"))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println html)
  (web/run app {:host "localhost" :port 8080 :path "/"}))

(comment
  (def started
    (web/run app-with-static {:host "localhost" :port 8080 :path "/app"})))
(comment
  (web/stop started))
