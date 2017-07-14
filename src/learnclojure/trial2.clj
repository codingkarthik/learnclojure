(defn hell
  [a b]
  (println "Hello World"))

(fn [x]
(loop [sum 0 cnt x ]
    (if (= cnt 0)
    sum
    (+ (recur (+ cnt sum) (dec cnt)) (recur (+ cnt sum) (- cnt 2))))))



(fn [n]
    (loop [cnt n acc 1]
       (if (or (= 1 cnt) (= 2 cnt))
            acc
          (+ (recur (dec cnt)) (recur (- cnt 2))))))

(def a (fn [n] (reverse (reduce (fn [a b] (conj a (+ (first a) (second a)))) '(1 1) (range 1 (- n 1))))))





(fn [x]
  (reduce (concat (fn [x]  (reduce + x) '(1 1)  x) x)))




(loop [x 10]
  (println x))

(fn [x]
  (if (string? x)
    (= (apply str (reverse x)) x)
    (= (reverse x) x)))


(defn flat [x]
  (if coll? (first x))
    (map flat (first x)) )
  (concat (first x) (flat (rest x)))

(defn fl [a e]
  (if (coll? e)
    (reduce fl a e)
    (conj a e)))

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

(defn my_sort
  [coll]
 ;(let [f (if (= com >) min max)]
  (println coll)
  (if (empty? coll)
    coll
    (conj (my_sort (my-remove-first (apply min coll) coll)) (apply min coll))))


(my_sort [1 2 3 2])


(defn my_remove
  [pred coll]
  (if (empty? coll)
    '()
    (do
      (if (pred (first coll))
        (my_remove pred (rest coll))
        (conj (my_remove pred (rest coll))
              (first coll))))))

(defn my-remove-first
  [a coll]
  (if (empty? coll)
    coll
    (if (= a (first coll))
      (rest coll)
      (cons
       (first coll) (my-remove-first a (rest coll))))))

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
