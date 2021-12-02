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
    (let [[f s] input]
      (if (nil? s)
        acc
        (recur (rest input) (if (> s f) (+ 1 acc) acc))))))