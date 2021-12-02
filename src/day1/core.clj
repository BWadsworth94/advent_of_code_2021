(ns day1.core
  (:require [clojure.java.io]))

(def input
  (->> (slurp "src/day1/input.txt")
       (clojure.string/split-lines)
       (map #(Integer/parseInt %))))

(defn count-increases
  [input]
  (loop [input input
         acc 0]
    (if (nil? (second input))
      acc
      (recur (rest input) (if (pos? (- (second input) (first input))) (+ 1 acc) acc)))))