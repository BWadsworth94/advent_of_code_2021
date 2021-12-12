(ns day8.core
  (:require [clojure.string :as s]))

(def input
  (->> "resources/day8/input.txt"
       slurp
       s/split-lines
       (map #(s/split % #" \| "))))

(def easy-digit-wire-counts
  [2 4 3 7])

(defn count-easy-numbers
  [input]
  (letfn [(parse-input [i] ((comp #(s/split % #" ") second) i))]
    (->> (map parse-input input)
         (reduce (fn [acc o]
                   (->> o
                        (map count)
                        frequencies
                        (merge-with + acc))) {})
         (#(select-keys % easy-digit-wire-counts))
         vals
         (apply +))))