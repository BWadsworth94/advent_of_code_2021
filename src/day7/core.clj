(ns day7.core)

(def test-input
  (-> "16,1,2,0,4,2,7,1,2,14"
      (clojure.string/split #",")
      (#(map (fn [s] (Integer/parseInt s)) %))))

(def input->vector
  (-> "resources/day7/input.txt"
      slurp
      (clojure.string/split #",")
      (#(map (fn [s] (Integer/parseInt s)) %))))

(defn abs-position-to-num
  [target candidate]
  (Math/abs (- candidate target)))

(defn fuel-costs
  [input]
  (reduce (fn [acc target]
            (->> input
                 (map #(abs-position-to-num target %))
                 (conj acc))) [] (distinct input)))

(defn part-1
  [input]
  (->> input
       fuel-costs
       (map #(apply + %))
       (apply min)))

(defn compounding-fuel-cost
  [input]
  (reduce (fn [acc target]
            (->> input
                 (pmap #(apply + (range 0 (inc (abs-position-to-num target %)))))
                 (conj acc))) [] (range 0 (apply max input)))) ;; I wonder if I can know the viable bounds

(defn part-2
  [input]
  (->> input
       compounding-fuel-cost
       (map #(apply + %))
       (apply min)))
