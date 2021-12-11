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


;; (apply mapv vector [[1 2 3] [4 5 6]])
;; [[1 4] [2 5] [3 6]]
;; clj꞉day3.core꞉> 

;; (mapv (fn [i x] (vector i x)) [1] [4])
;; [[1 4]]
;; clj꞉day3.core꞉> 


;; (->> (apply mapv + [[1 1] [0 1] [0 1] [0 0]])
;;      (map #(> % 2)))
;; [5 7]
;; clj꞉day3.core꞉> 
;; [1 3]
;; clj꞉day3.core꞉> 
;; ; Execution error (ClassCastException) at day3.core/eval7552 (REPL:11).
;; ; class clojure.lang.PersistentVector cannot be cast to class java.lang.Number (clojure.lang.PersistentVector is in unnamed module of loader 'app'; java.lang.Number is in module java.base of loader 'bootstrap')
;; clj꞉day3.core꞉> 
;; ; Error printing return value (ClassCastException) at clojure.core/map$fn (core.clj:2757).
;; ; class clojure.lang.Ratio cannot be cast to class clojure.lang.IFn (clojure.lang.Ratio and clojure.lang.IFn are in unnamed module of loader 'app')
;; clj꞉day3.core꞉> 
;; (2 2/3)
;; clj꞉day3.core꞉> 
;; (false true)
;; clj꞉day3.core꞉> 
