(ns hello-rum.core
  (:require
    [rum.core :as rum]))

(rum/defc my-comp [s] [:div s])

(defn mount! [mount-el]
  (rum/mount (my-comp "XXX") mount-el))

(defn el [id]
  (js/document.getElementById id))

(mount! (el "my-hello-component"))