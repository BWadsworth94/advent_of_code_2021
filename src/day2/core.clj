(ns day2.core)

(def input
  (-> "resources/day2/input.txt"
      slurp
      clojure.string/split-lines))

(defn instruction->tuple
  [instruction]
  (-> (clojure.string/split instruction #" ")
      (update-in [1] #(Integer/parseInt %))))

(defn multiply-results
  [{:keys [depth forward]}]
  (* depth forward))

(defn movements-map-1
  [instructions]
  (reduce (fn [acc [dir step]]
            (case dir
              "forward" (update acc :forward + step)
              "up" (update acc :depth - step)
              "down" (update acc :depth + step)))
          {:forward 0
           :depth 0} instructions))

(defn part-1
  [input]
  (->> input
       (map instruction->tuple)
       movements-map-1
       multiply-results))


(defn movements-map-2
  [instructions]
  (reduce (fn [{:keys [aim] :as acc} [dir step]]
            (case dir
              "forward" (-> acc
                            (update :forward + step)
                            (update :depth + (* aim step)))
              "up" (update acc :aim - step)
              "down" (update acc :aim + step)))
          {:forward 0
           :depth 0
           :aim 0} instructions))

(defn part-2
  [input]
  (->> input
       (map instruction->tuple)
       movements-map-2
       multiply-results))