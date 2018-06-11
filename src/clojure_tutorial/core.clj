(ns clojure-tutorial.core
  (require [clojure.string :as str])
  (:gen-class))

; ATOMS
;
(defn atom-ex
  [x]
  (def atomEx (atom x))
  (add-watch atomEx :wather
             (fn [key atom old-state new-state]
               (println "atomEx changed from " old-state " to " new-state)))
  (println "1st x" @atomEx)
  (reset! atomEx 10)
  (println "2nd x" @atomEx)
  (swap! atomEx inc)
  (println "Increment x" @atomEx))

;
; AGENTS
;
(defn agent-ex
  []
  (def tickets-sold (agent 0))
  (send tickets-sold + 15)
  (await-for 100 tickets-sold)
  (println "Tickets sold: " @tickets-sold)

  ; shutdown agents
  (shutdown-agents))

;
; FUNCTIONS
;

(defn say-hello
  "Receives a name with 1 parameter and responds"
  [name]
  (println "Hello again" name "!"))

(defn get-sum
  "Sums 2 numbers"
  [x y]
  (+ x y))

; Returns a sum depending of the n of args
(defn get-sum-more
  ([x y z]
   (+ x y z))

  ([x y]
   (+ x y)))

(defn hello-you
  [name]
  (str "Hello " name))

(defn hello-all
  [& names]
  (map hello-you names))

;
; LOGICAL (and or not) & RELATIONAL (= not= <) OPERATORS
;

(defn can-vote
  [age]
  (if (>= age 18)
    (println "You can vote!")
    ; else
    (println "You can't vote :(")))

(defn can-do-more
  [age]
  (if (>= age 18)
    (do (println "You can drive")
        (println "You can Vote"))
    (println "You can't vote")))

(defn when-ex
  [tof] ; true or false
  (when tof
    (println "1st thing")
    (println "2nd thing")))

(defn what-grade
  [n]
  (cond
    (< n 6) (println "Preschool")
    (= n 6) (println "Kindergarten")
    (and (> n 6) (<= n 18)) (println (format "Go to grade %d" (- n 5)))
    :else (println "Go to College!")))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; LOOPS
;
(defn one-to-x
  [x]
  (def i (atom 1))
  (while (<= @i x)
    (do
      (println @i)

      ; swap! currentValue nextValue
      (swap! i inc))))

(defn double-to-x
  [x]
  (dotimes [i x]
    (println(* i 2))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;main

(defn -main
  "I don't do a whole lot ... yet."
  [& args]

  ;;;;;;;;;;;;;;;;;;;;;;;
  ; STRINGS
  ;
  (println "\nStrings:")

  (def str1 "This is my string")

  ; Need to require str
  ; Test if string is blank
  (println (str/blank? str1))

  ; Does it include?
  (println (str/includes? str1 "my"))

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

  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
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


  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
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

  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
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

  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  ; MAPS
  ;
  (println "\nMaps:")

  ; hash-map
  (println "hash-map," (hash-map "Name" "Simeon" "Age" 27))

; sorted-map
  (println "sorted-map," (sorted-map 5 12 7 "simeon" 3 "Banana"))

; Get the value of a key
  (println "get," (get (hash-map "name" "Simeon" "age" 27) "age"))

; Find the key and value
  (println "find," (find (hash-map "name" "Simeon" "age" 27) "age"))

; Does it contain?
  (println "contains?," (contains? (hash-map "name" "Simeon" "age" 27) "age"))

; Get a list of keys
  (println "keys," (keys (hash-map "name" "Simeon" "age" 27)))

; Get the values
  (println "vals," (vals (hash-map "name" "Simeon" "age" 27)))

; Merge maps
  (println "merge-with," (merge-with + (hash-map "name" "Simeon" "age" 27) (hash-map "address" "helsinki")))

  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  (println "\nFunctions:")
  ; 1st x 5)
  ; atomEx changed from  5  to  10
  ; 2nd x 10
  ; atomEx changed from  10  to  11
  (atom-ex 5)

  ; Tickets sold:  15
  (agent-ex)

  (say-hello "Simeon")

  ; 3
  (println (get-sum 1 2))

  ; 3
  (println (get-sum-more 1 1 1))

  ; 2
  (println (get-sum-more 1 1))

  ; Hello Me!
  (println (hello-you "Me!"))

  ; (Hello Doug Hello Mary Hello James)
  (println (hello-all "Doug" "Mary" "James"))

  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  ; CONDITIONALS

  (println "\nConditionals:")
  ; You can vote!
  (println (can-vote 19))

  ; You can drive)
  ; You can Vote
  (println (can-do-more 18))

  (when-ex true)

  (what-grade 18)

  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  ; LOOPS
  (println "\nLoops:")

  ; 1 2 3
  (one-to-x 3)
  (println)

  ; 0 2 4 6
  (double-to-x 4))
