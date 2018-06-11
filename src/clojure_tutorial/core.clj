(ns clojure-tutorial.core
  (require [clojure.string :as str])
  (:gen-class)

  (defn -main
    "I don't do a whole lot ... yet."
    [& args]

    ; STRINGS
    ;
    (println "\nStrings:")

    (def str1 "This is my string")

    ; Need to require str
    ; Test if string is blank
    (println
      (str/blank? str1)

      ; Does it include?
      (println
        (str/includes? str1 "my")

        ; Split into vector
        (println (str/split str1 #" "))
        (println (str/split str1 #"\d"))

        ; Join a collection
        (println (str/join " " ["The" "Big" "Cheese"]))

        ; Replace
        (println (str/replace "I am 27" #"27" "28"))

        ; Trim left / right
        (println (str/triml "  left trimmed!"))

        ; Uppercase
        (println (str/upper-case "my string"))

        ;
        ; LISTS
        ;
        (println "\nLists:")

        (println (list "Cat" 1 5.666 true))

        ; Prints the first item
        (println (first (list 1 2 3)))

        ; Print all but first
        (println (rest (list 1 2 3)))

        ; Add items 3 & 4
        (println (list* 1 2 [3 4]))

        ; Add to the beginning
        (println (cons 3 (list 1 2)));

        ; SETS
        ; Are lists of unique values
        ;
        (println "\nSets:")

        ; Only shows unique

        (println (set '(1 1 2 3)))

        ; Get the value from set (if it contains)
        (println (get (set '(1 1 3 4)) 4))

        ; Adds a value to the end if it doesn't contain
        (println (conj (set '(1 1 3 4)) 11))

        ; Does it contain a value
        (println (contains? (set '(1 1 3 4)) 11))

        ; Remove, disjoin
        (println (disj (set '(1 3)) 1))


        ;VECTORS
        ;
        (println "\nVectors:")

        ; index of
        (println (get (vector 3 2) 1))

        ; conjoin
        (println (conj (vector 3 4) 5))

        ; pop's the last item from vector
        (println "pop," (pop (vector 3 2 3 3 3)))

        ; returns from range 0 to 3
        (println "subvec," (subvec (vector 2 4 6 8) 0 3))

        ; MAPS
        ;

        ; hash-map
        (println "hash-map,"(hash-map "Name" "Simeon" "Age" 27))


        ; sorted-map
        (println "sorted-map,"(sorted-map "Name" "Simeon" "Age" 27))))))




        ; 6.10
        ; run $ lein auto run
