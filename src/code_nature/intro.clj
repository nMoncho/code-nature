(ns code-nature.intro
  (:gen-class)
  (:require [code-nature.core :as core]
            [quil.core :as q]))

;; Define a walker that start at the center of the sketch
(defn walker-setup []
  (q/frame-rate 30)
  {:x (/ (q/width) 2)
   :y (/ (q/height) 2)})

;; Drawer function that just print a point of the position of the walker
(defn walker-draw
  [state]
  (q/stroke 0)
  (q/point (:x state) (:y state)))

(defn exec1-update 
  [state]
  (let [choise (int (* 4 (Math/random)))]
    (cond
     (= choise 0) {:x (inc (:x state)) :y (:y state)}
     (= choise 1) {:x (dec (:x state)) :y (:y state)}
     (= choise 2) {:x (:x state) :y (inc (:y state))}
     :else {:x (:x state) :y (dec (:y state))})))

(defn exec2-update
  [state]
  (let [dx (core/random -1 1)
        dy (core/random -1 1)]
    {:x (+ dx (:x state))
     :y (+ dy (:y state))}))

(def exec1 (core/create-sketch
            "Exec1. Random Walker"
            walker-setup
            exec1-update
            walker-draw))

(def exec2 (core/create-sketch
            "Exec2. Random Walker"
            walker-setup
            exec2-update
            walker-draw))
