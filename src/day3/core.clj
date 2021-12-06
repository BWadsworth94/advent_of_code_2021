(ns day3.core
  (:require [clojure.string :as s]))

(def report
  (slurp "resources/day3/input.txt"))

(defn report->records
  [r]
  (s/split-lines r))

(defn transpose
  [records]
  (apply mapv vector records))

(defn zero-most-common
  [freqs]
  (if (> (get freqs \0 0) (get freqs \1 0)) true false))

(def one-most-common
  (complement zero-most-common))

(defn ?-is-most-common
  [fn records]
  (->> records
       transpose
       (map frequencies)
       (map fn)))

(defn binary-vector->integer
  [vector]
  (-> vector
      s/join
      (#(Integer/parseInt % 2))))

(defn boolean->bit
  [bool]
  (if bool 1 0))

(defn part-1
  []
  (let [records (->> report report->records)
        gamma (->> records
                   (?-is-most-common one-most-common)
                   (map boolean->bit)
                   (#(binary-vector->integer %)))
        epsilon (->> records
                     (?-is-most-common zero-most-common)
                     (map boolean->bit)
                     (#(binary-vector->integer %)))]
    (* gamma epsilon)))

(defn part-2
  [input]
  (->> ["1001" "1101"]
       (?-is-most-common one-most-common)))