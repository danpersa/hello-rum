(ns hello-rum.components
  (:require
    [rum.core :as rum]))

(rum/defc my-comp [s] [:div s])

(rum/defcs local-state < (rum/local 0)
           [state title]
           (let [*count (:rum/local state)]
             [:div
              {:style {"-webkit-user-select" "none"
                       "cursor" "pointer"}
               :class {"clicker"}
               :on-click (fn [_] (swap! *count inc)) }
              title ": " @*count]))
