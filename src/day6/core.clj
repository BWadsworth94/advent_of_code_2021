(ns day6.core
  (:require [clojure.test :as t]))

(def test-input
  [3 4 3 1 2])

(frequencies test-input)

(defn iterate-day
  [m]
  (->> m
       (map (fn [[k v]]
              (if (zero? k)
                {8 v
                 6 v}
                {(dec k) v})))
       (apply merge-with +)))

(defn count-fish
  [m]
  (->> m
       vals
       (apply +)))

(defn part-1
  [input num-of-days]
  (->> input
       (iterate iterate-day)
       (#(nth % num-of-days))
       count-fish))

(-> "resources/day6/input.txt"
    slurp
    (clojure.string/split #",")
    (#(map (fn [s] (Integer/parseInt s)) %))
    frequencies
    (part-1 80))


;; (t/deftest day-2-test
;;   (let [given (frequencies [3 4 3 1 2])
;;         expected (frequencies [1 2 1 6 0 8])]
;;     (t/is (= (->> given
;;                   iterate-day
;;                   iterate-day)
;;              expected))))