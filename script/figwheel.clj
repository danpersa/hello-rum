(require '[figwheel-sidecar.repl-api :as ra]
         '[com.stuartsierra.component :as component])

;; this will start figwheel and will start autocompiling the builds specified in `:builds-ids`
(def figwheel-config
  {:figwheel-options {:css-dirs ["resources/public/css"]} ;; <-- figwheel server config goes here
   :build-ids ["dev"]   ;; <-- a vector of build ids to start autobuilding
   :all-builds          ;; <-- supply your build configs here
                     [{:id "dev"
                       :figwheel true
                       :source-paths ["src"]
                       :compiler {:main "hello-rum.core"
                                  :asset-path "js/out"
                                  :output-to "resources/public/js/main.js"
                                  :output-dir "resources/public/js/out"
                                  :verbose true}}]})

(def sass-config
  {:executable-path "sass" ; e.g. /usr/local/bin/sass
   :input-dir "sass" ; location of the sass/scss files
   :output-dir "resources/public/css"})

(defrecord Figwheel []
  component/Lifecycle
  (start [config]
    (ra/start-figwheel! config)
    config)
  (stop [config]
    (ra/stop-figwheel!)
    config))

(defrecord SassWatcher [executable-path input-dir output-dir]
  component/Lifecycle
  (start [config]
    (if (not (:sass-watcher-process config))
      (do
        (println "Figwheel: Starting SASS watch process")
        (assoc config :sass-watcher-process
                      (.exec (Runtime/getRuntime)
                             (str executable-path " --watch " input-dir ":" output-dir))))
      config))
  (stop [config]
    (when-let [process (:sass-watcher-process config)]
      (println "Figwheel: Stopping SASS watch process")
      (.destroy process))
    config))

(def system
  (atom
    (component/system-map
      :figwheel (map->Figwheel figwheel-config)
      :sass (map->SassWatcher sass-config))))

(defn start []
  (swap! system component/start))

(defn stop []
  (swap! system component/stop))

(defn reload []
  (stop)
  (start))

(defn repl []
  (ra/cljs-repl))

;; Start the components and the repl
(start)
(repl)

;; you can also just call (ra/start-figwheel!)
;; and figwheel will do its best to get your config from the
;; project.clj or a figwheel.edn file

;; you can optionally supply a build id
;; (ra/cljs-repl "dev")