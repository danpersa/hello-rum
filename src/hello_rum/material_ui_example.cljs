(ns hello-rum.material-ui-example
  (:require [cljsjs.material-ui]  ; I recommend adding this at the beginning of core file
    ;  so React is always loaded first. It's not always needed
            [cljs-react-material-ui.core :as ui]
            [cljs-react-material-ui.icons :as ic]
            [cljs-react-material-ui.rum :as rui]
            [rum.core :as rum]))

(defn home-page []
  (rui/mui-theme-provider
    {:mui-theme (ui/get-mui-theme)}
    [:div
     (rui/app-bar {:icon-element-right (rui/icon-button (ic/action-accessibility))})
     (rui/tabs
       (rui/tab {:label "one"}
                [:div ["hey"
                       (rui/paper "yes")]])
       (rui/tab {:label "two"} (thing1))
       (rui/tab {:label "drei"}
                [:div
                 (rui/paper {} "Ima paper")]))]))

(rum/defc thing1
          []
          [:div home-page])
