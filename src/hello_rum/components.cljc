(ns hello-rum.components
  (:require
    [rum.core :as rum]
    [rum.mdl :as mdl]))

(rum/defc my-comp [s] [:div s])

(rum/defcs local-state < (rum/local 0)
           [state title]
           (let [*count (:rum/local state)]
             [:div
              {:style {"-webkit-user-select" "none"
                       "cursor" "pointer"}
               :on-click (fn [_] (swap! *count inc)) }
              title ": " @*count]))

(rum/defc tables []
  (mdl/table
    {:mdl [:selectable :shadow--2dp]}
    (mdl/thead
      [{:mdl [:non-numeric]}]
      ["Material" "Quantity" "Unit price"])
    (mdl/tbody
      [{:mdl [:non-numeric]}]
      [["Acrylic (Transparent)" "25" "$2.90"]
       ["Plywood (Birch)" "50" "$1.25"]
       ["Laminate (Gold on Blue)" "10" "$2.35"]])))

(rum/defc button [name]
  (mdl/button {:mdl [:raised]} name))