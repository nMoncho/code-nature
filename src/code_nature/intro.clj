(ns code-nature.intro
  (:gen-class)
  (:require [code-nature.core :as core]
            [quil.core :as q]))

(defn exec1-setup []
  (q/frame-rate 30)
  {:x (/ (q/width) 2)
   :y (/ (q/height) 2)})

(defn exec1-update 
  [state]
  (let [choise (int (* 4 (Math/random)))]
    (cond
     (= choise 0) {:x (inc (:x state)) :y (:y state)}
     (= choise 1) {:x (dec (:x state)) :y (:y state)}
     (= choise 2) {:x (:x state) :y (inc (:y state))}
     :else {:x (:x state) :y (dec (:y state))})))

(defn exec1-draw
  [state]
  (q/stroke 0)
  (q/point (:x state) (:y state)))

(def exec1 (core/create-sketch
            "Exec1. Random Walker"
            exec1-setup
            exec1-update
            exec1-draw))
