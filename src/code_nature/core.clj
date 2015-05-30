(ns code-nature.core
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn create-sketch
  "Creates a map defining a sketch"
  [title setup-fn update-fn draw-fn]
  {:title title
   :setup setup-fn
   :update update-fn
   :draw draw-fn})

(defn start-sketch
  [{title :title setup :setup update :update draw :draw}]        ; maybe I can gather with 'keys'
  (q/sketch
    :title title
    :size [500 500]             ;should also add this param
    :setup setup
    :update update
    :draw draw
    :middleware [m/fun-mode]))

(defn lookup-sketch
  [ns name]
  (eval (symbol (str "code-nature." ns) name)))

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 30)
  ; Set color mode to HSB (HSV) instead of default RGB.
  (q/color-mode :hsb)
  ; setup function returns initial state. It contains
  ; circle color and position.
  {:color 0
   :angle 0})

(defn update-state [state]
  ; Update sketch state by changing circle color and position.
  {:color (mod (+ (:color state) 0.7) 255)
   :angle (+ (:angle state) 0.1)})

(defn draw-state [state]
  ; Clear the sketch by filling it with light-grey color.
  (q/background 240)
  ; Set circle color.
  (q/fill (:color state) 255 255)
  ; Calculate x and y coordinates of the circle.
  (let [angle (:angle state)
        x (* 150 (q/cos angle))
        y (* 150 (q/sin angle))]
    ; Move origin point to the center of the sketch.
    (q/with-translation [(/ (q/width) 2)
                         (/ (q/height) 2)]
      ; Draw the circle.
      (q/ellipse x y 100 100))))

(def test-sketch
  (create-sketch "You spin my circle" setup update-state draw-state))

(defn random 
  ([max] (rand max))
  ([min max] (+ min (rand (- max min)))))

(defn -main
  "Main method, executes the specified sketch
   USAGE: lein run intro exec1"
  [& args]
  (let [[ns name] args]
    (if (= 2 (count args)) 
     (start-sketch (lookup-sketch ns name))
     (throw (IllegalArgumentException. "USAGE: lein run intro exec1")))))

#_(q/defsketch code-nature
  :title "You spin my circle right round"
  :size [500 500]
  ; setup function called only once, during sketch initialization.
  :setup setup
  ; update-state is called on each iteration before draw-state.
  :update update-state
  :draw draw-state
  ; This sketch uses functional-mode middleware.
  ; Check quil wiki for more info about middlewares and particularly
  ; fun-mode.
  :middleware [m/fun-mode])

