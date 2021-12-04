(ns day3.core
  (:require [clojure.string :as s]))

(def input
  (-> "resources/day3/input.txt"
      slurp
      s/split-lines))

(defn transpose
  [vectors]
  (apply mapv vector vectors))

(defn get-num
  [input most-common-fn]
  (->> input
       (map #(s/split % #"")) ;; ["1001"] -> ["1" "0" "0" "1"]
       (map (partial map #(s/replace % #"0" "-1"))) ;; ["1" "0" "0" "1"] -> ["1" "-1" "-1" "1"]
       (map (partial map #(Integer/parseInt %))) ;; str to int
       transpose ;; [[1 0 1] [1 1 0]] -> [[1 1] [0 1] [1 0]]
       (map #(apply + %)) ;; add the vectors
       (map (fn [b] (if (most-common-fn b) 1 0))) ;; if adds to pos or negative give 1 or 0
       s/join ;; smash the vector into a string
       (#(Integer/parseInt % 2)))) ;; convert it to dec

(defn part-1
  [input]
  {:gamma (get-num input pos-int?)
   :epsilon (get-num input (complement pos-int?))})

(comment
  (part-1 input))