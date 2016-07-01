(defproject hello-rum "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript  "1.8.51"]
                 [cljsjs/react               "15.1.0-0"]
                 [cljsjs/react-dom           "15.1.0-0"]
                 [org.immutant/immutant "2.1.4"]
                 [ring/ring-core "1.4.0"]
                 [rum "0.10.1"]
                 [rum-mdl "0.1.0"]
                 [figwheel-sidecar "0.5.1"]
                 [com.stuartsierra/component "0.3.1"]]
  :main ^:skip-aot hello-rum.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :plugins [[lein-cljsbuild "1.1.2"]
            [lein-figwheel "0.5.3-2"]]

  :cljsbuild
  {:builds
   [{:id           "advanced"
     :source-paths ["src"]
     :compiler
                   {:main           hello-rum.core
                    :output-to      "resources/public/js/main.js"
                    :output-dir     "resources/public/js/advanced"
                    :optimizations  :advanced
                    :source-map     "resources/public/js/main.js.map"
                    :pretty-print   false
                    :compiler-stats true
                    :parallel-build true}}

    {:id           "dev"
     :source-paths ["src"]
     :figwheel     true
     :compiler
                   {:main                 hello-rum.core
                    :asset-path           "js/out"
                    :output-to            "resources/public/js/main.js"
                    :output-dir           "resources/public/js/out"}}

    {:id           "none"
     :source-paths ["src"]
     :compiler
                   {:main           hello-rum.core
                    :output-to      "target/main.js"
                    :output-dir     "target/none"
                    :asset-path     "target/none"
                    :optimizations  :none
                    :source-map     true
                    :compiler-stats true
                    :parallel-build true}}
    ]}
  :figwheel {
     :css-dirs ["resources/public/css"]}
  )
