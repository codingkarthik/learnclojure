(ns learnclojure.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn hell
  [a b]
  "Hello World")

(defn add1
  [a b]
  (+ a b))

(fn [x]
  (reduce (concat (fn [x]  (reduce + x) '(1 1)  x) x)))

(loop [x 10]
  (println x))

(fn [x]
  (if (string? x)
    (= (apply str (reverse x)) x)
    (= (reverse x) x)))

(fn [l]
  (reverse (reduce fl '() l)))

(defn flat [x]
  (if (coll? x)
    (mapcat flat x)
    [x]))
    #_(let [head (first x)
          tail (rest x)]
      (println head)
      (concat (flat head) (if (empty? tail)
                            tail
                            (flat tail)))
    [x])

(def compress (fn [a b]
  (if-not (= (first b) (last a))
    b)))

(defn my_take
  [n coll]
  (if (= n 0)
      ()
  (conj (my_take (dec n) (rest coll)) (first coll))))

(defn my_drop
  [n coll]
  (if (> n 0)
    (my_drop (dec n) (rest coll))
    coll))

(defn my_take-while
  [pred coll]
  (if (pred (first coll))
    (conj (my_take-while pred (rest coll)) (first coll))
    ()))

(defn my_drop-while
  [pred coll]
  (if-not (pred (first coll))
    coll
    (my_drop-while pred (rest coll))))

(defn my_some
  [pred coll]
  (if (pred (first coll))
    (first coll)
    (my_some pred (rest coll))))

(defn my-remove-first
  [a coll]
  (if (empty? coll)
    coll
    (if (= a (first coll))
      (rest coll)
      (cons
       (first coll) (my-remove-first a (rest coll))))))

(defn my_sort
  [coll]
 ;(let [f (if (= com >) min max)]
  (println coll)
  (if (empty? coll)
    coll
    (conj (my_sort (my-remove-first (apply min coll) coll)) (apply min coll))))

(defn my_remove
  [pred coll]
  (if (empty? coll)
    '()
    (do
      (if (pred (first coll))
        (my_remove pred (rest coll))
        (conj (my_remove pred (rest coll))
              (first coll))))))



(defn my-complement
  [f]
  (fn  [& args]
    (not (apply f args))))

(defn min2
  [coll]
  (reduce (fn [a b]
            (if (< (second a) (second b)) a b))
          coll))

(defn create-map
  [func coll]
  (if (empty? coll)
    coll
    (conj (create-map func (rest coll))
          (vector (first coll)
                  (func (first coll))))))

(defn my-remove-first2
  [a coll]
  (if (empty? coll)
    coll
    (if (= a (first coll))
      (rest coll)
      (conj (my-remove-first2 a (rest coll))
            (first coll)))))

(defn my_sort2
  [coll]
  (if (empty? coll)
    coll
    (let [rslt (min2 coll)]
      (conj (my_sort2 (my-remove-first2 rslt coll))
            rslt))))

(defn my-sort-by
  [keyfn coll]
  (let [a (create-map keyfn coll)]
    (my_sort2 a)))
