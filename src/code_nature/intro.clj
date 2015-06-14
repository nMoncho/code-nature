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

(defn -exam3-distro
  [rnd]
  (cond
   (< rnd 0.4) {:key :x :fun inc}
   (< rnd 0.6) {:key :x :fun dec}
   (< rnd 0.8) {:key :y :fun inc}
   :else {:key :y :fun dec}))

(defn exam3-update
  [state]
  (let [rnd (core/random)
        {key :key fun :fun} (-exam3-distro rnd)]
    (update-in state [key] fun)))

(def exam3 (core/create-sketch
            "Example 2. Walker that tends to move to the right"
            walker-setup
            exam3-update
            walker-draw))
(defn -exec3-goto-mouse
  [state mx my]
  (let [{x :x y :y} state
        dx (- mx x)
        dy (- my y)
        dx (/ dx (Math/abs dx))
        dy (/ dy (Math/abs dy))]
    {:x (+ x dx) :y (+ y dy)}))

(defn exec3-update
  [state]
  (let [mx (q/mouse-x)
        my (q/mouse-y)
        rnd (core/random)]
    (cond
     (< rnd 0.5) (-exec3-goto-mouse state mx my)
     :else (exec2-update state))))

(def exec3 (core/create-sketch
            "Create a random walker with dynamic probabilities. 50% chance of moving in the direction of the mouse"
            walker-setup
            exec3-update
            walker-draw))

(defn rnd-distro-setup []
  (q/frame-rate 30)
  {:randomCounts (apply vector(take 20 (repeat 0)))})

(defn rnd-distro-update 
  [state]
  (let [rnd-size (count (:randomCounts state))
        idx (int (core/random rnd-size))]
    (update-in state [:randomCounts idx] inc)))

(defn rnd-distro-draw
  [state]
  (q/background 255)
  (q/stroke 0)
  (q/fill 175)
  (let [{randoms :randomCounts} state
        randoms (map-indexed vector randoms)
        w (/ (q/width) (count randoms))
        height (q/height)]
    (doseq [rnd randoms]
      (let [idx (first rnd)
            val (last rnd)] 
        (q/rect (* idx w) (- height val) w val)))))

(def exam1 (core/create-sketch
              "Random number distribution"
              rnd-distro-setup
              rnd-distro-update
              rnd-distro-draw))
