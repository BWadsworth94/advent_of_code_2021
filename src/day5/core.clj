(ns day5.core
  (:require [clojure.string :as s]))

(def test-input
  "0,9 -> 5,9\n8,0 -> 0,8\n9,4 -> 3,4\n2,2 -> 2,1\n7,0 -> 7,4\n6,4 -> 2,0\n0,9 -> 2,9\n3,4 -> 1,4\n0,0 -> 8,8\n5,5 -> 8,2")

(defn input->vectors
  [input]
  (->> input
       s/split-lines
       (map #(s/split % #"\s->\s"))
       (mapv (fn [[f s]]
              (let [split-fn (fn [s] (s/split s #","))]
                [(mapv (fn [s] (Integer/parseInt s)) (split-fn f)) 
                 (mapv (fn [s] (Integer/parseInt s)) (split-fn s))])))))

(defn diagnol?
  [cords]
  (->> cords
       (apply mapv (comp zero? -))
       (some #{true})
       not))

(input->vectors test-input)

(->> test-input
     input->vectors
     (remove diagnol?))


(->> [[[0 9] [5 9]]]
     (reduce (fn [acc cords]
               (let [ancor (first cords)
                     movement (->> (apply mapv - cords))
                     move-on-?-axis (if (zero? (first movement)) :y :x)
                     start-of-line (if (= :x move-on-?-axis) (first ancor) (second ancor))
                     end-of-line (- start-of-line ((comp first #(remove zero? %)) movement))]
                 (prn ancor movement move-on-?-axis)
                 (prn start-of-line (inc end-of-line)))) {}))