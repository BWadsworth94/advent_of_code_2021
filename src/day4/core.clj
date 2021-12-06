(ns day4.core
  (:require [clojure.string :as s]))

(def input
  (slurp "resources/day4/input.txt"))

(defn transpose
  [vectors]
  (apply mapv vector vectors))

(defn get-moves
  "Pull the moves from the input and put them in a vector."
  [input]
  (-> input
      (s/split #"\n\n")
      first
      (s/split #",")))

(def board-text->vectors
  "1 2 3\n4 5 6\n\n.. => [[[1 2 3] [4 5 6]] [...]]"
  (comp 
   #(map (fn [s] (s/split s #"\s+")) %) 
   #(s/split % #"\n")))

(defn get-winning-combinations
  [board]
  (let [horizontals board
        verticals   (transpose horizontals)]
    (concat horizontals verticals)))

(defn get-boards
  "Vector where each board is a vector where each row is a vector and each column is a vector"
  [input]
  (-> input
      (s/split #"\n\n")
      rest
      (#(map board-text->vectors %))
      (#(map get-winning-combinations %))))

(defn remove-number
  [number board]
  (map (fn [combinations] (remove #(= number %) combinations)) board))

(defn winner?
  [board]
  (->> board
       (keep empty?)
       (some true?)
       boolean))

(defn get-first-winner
  [boards]
  (reduce (fn [_ board]
            (if (winner? board)
              (reduced board)
              false)) nil boards))

(defn sum-leftovers
  [board]
  (->> board
       flatten
       distinct
       (map (fn [s] (Integer/parseInt s)))
       (apply +)))

(defn part-1
  []
  (let [moves (get-moves input)
        boards (->> (get-boards input)
                    (map get-winning-combinations))]
    (reduce (fn [boards move]
              (let [updated-boards (map #(remove-number move %) boards)
                    winner (get-first-winner updated-boards)]
                (if winner
                  (reduced (* (sum-leftovers winner) (Integer/parseInt move)))
                  updated-boards))) boards moves)))
