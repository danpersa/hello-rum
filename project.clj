(defproject hello-rum "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript  "1.8.51"]
                 [cljsjs/react               "15.0.1-1"]
                 [cljsjs/react-dom           "15.0.1-1"]
                 [org.immutant/immutant "2.1.4"]
                 [ring/ring-core "1.4.0"]
                 [rum "0.8.3"]]
  :main ^:skip-aot hello-rum.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :plugins [ [lein-cljsbuild "1.1.2"] ]

  :cljsbuild
  { :builds
   [{ :id "advanced"
     :source-paths ["src"]
     :compiler
     { :main           hello-rum.core
      :output-to      "target/main.js"
      :optimizations  :advanced
      :source-map     "target/main.js.map"
      :pretty-print   false
      :compiler-stats true
      :parallel-build true }}

    { :id "none"
     :source-paths ["src"]
     :compiler
     { :main           hello-rum.core
      :output-to      "target/main.js"
      :output-dir     "target/none"
      :asset-path     "target/none"
      :optimizations  :none
      :source-map     true
      :compiler-stats true
      :parallel-build true }}
    ]}
  )
