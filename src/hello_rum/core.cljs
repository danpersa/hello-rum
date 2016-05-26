(ns hello-rum.core
  (:require
    [rum.core :as rum]
    [hello-rum.components :as comp]))

(defn el [id]
  (js/document.getElementById id))

(defn mount-my-comp! [mount-el]
  (rum/mount (comp/my-comp "XXX") mount-el))

(defn mount-local-state! [mount-el]
  (rum/mount (comp/local-state "Clicks count") mount-el))

(mount-my-comp! (el "my-hello-component"))

(mount-local-state! (el "local-state"))