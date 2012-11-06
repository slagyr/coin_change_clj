(ns coin-change-spec
  (:require [speclj.core :refer :all ]))

(defn change-for [amt]
  (let [denominations [25 10 5 1]
        amounts (reductions #(rem %1 %2) amt denominations)
        coins (map #(int (/ %1 %2)) amounts denominations)]
    (mapcat #(take %1 (repeat %2)) coins denominations)))

(describe "Coin Changer"

  (for [[amount change]
        [
          [0 []]
          [1 [1]]
          [2 [1 1]]
          [3 [1 1 1]]
          [4 [1 1 1 1]]
          [5 [5]]
          [6 [5 1]]
          [10 [10]]
          [11 [10 1]]
          [16 [10 5 1]]
          [20 [10 10]]
          [25 [25]]
          [26 [25 1]]
          [27 [25 1 1]]
          [28 [25 1 1 1]]
          [30 [25 5]]
          [99 [25 25 25 10 10 1 1 1 1]]
          ]]
    (it (str "changes " amount)
      (should= change (change-for amount))))

  )