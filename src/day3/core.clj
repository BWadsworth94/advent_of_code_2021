(ns day3.core)

(def input
  (-> "resources/day3/input.txt"
      slurp
      clojure.string/split-lines))

(defn ->index-mapping
  [instruction]
  (->> instruction
       (map-indexed (fn [idx item]
                      {idx [item]}))
       (apply merge)))

(defn ->indexed-binary-values
  [binary-instructions]
  (reduce (fn [acc i]
            (merge-with into acc (->index-mapping i))) 
          {} 
          binary-instructions))

(defn ->indexed-binary-frequencies
  [m]
  (mapv (fn [[idx binary-values]]
         {idx (frequencies binary-values)}) m))

(defn most-common-binary
  [m]
  (let [freq-zeros (get m \0 0)
        freq-ones  (get m \1 0)]
    (if (> freq-zeros freq-ones) 0 1)))

(defn part-1
  [input use-least-common?]
  (->> input
       ->indexed-binary-values
       ->indexed-binary-frequencies
       (map (fn [freq-mappings]
              (let [binary-counts-map (first (vals freq-mappings))
                    most-common (most-common-binary binary-counts-map)]
                (if use-least-common?
                  (- 1 most-common)
                  most-common)
               )))
       clojure.string/join
       (#(Integer/parseInt % 2))))

(comment
  (part-1 input true)
  (part-1 input false)