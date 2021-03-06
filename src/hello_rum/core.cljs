(ns hello-rum.core
  (:require
    [rum.core :as rum]
    [hello-rum.components :as comp]
    [hello-rum.material-ui-example :as me]))

(defn el [id]
  (js/document.getElementById id))

(defn mount-my-comp! [mount-el]
  (rum/mount (comp/tables) mount-el))

(defn mount-local-state! [mount-el]
  (rum/mount (comp/local-state "Click count") mount-el))

(defn mount-my-button! [mount-el]
  (rum/mount (comp/button "Hello Button") mount-el))

(defn mount-home-page! [mount-el]
  (rum/mount (me/home-page) mount-el))

(mount-my-comp! (el "my-hello-component"))

(mount-local-state! (el "local-state"))

(mount-my-button! (el "button-example"))

(mount-home-page! (el "material-ui-example"))
