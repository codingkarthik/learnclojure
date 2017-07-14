(ns learnclojure.core-test
  (:require [clojure.test :refer :all]
            [learnclojure.core :refer :all]
            [expectations :as expect]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))

(expectations/expect nil? nil)

(expectations/expect "Hello World" (hell 1 2))

(expectations/expect 3 (add1 1 2))

(expectations/expect [1 2 3 4] (flat [1 2 [3 4]]))

(expectations/expect ["a"] ((((("a"))))))
