(ns clojure-tutorial.core
  (require [clojure.string :as str])
  (:gen-class))

(use 'clojure.java.io)

(defmacro discount
  ([cond dis1 dis2]
   ; Syntax quoting `
   ; Dont evaluate, just return the if statement
   (list `if cond dis1 dis2)))

(defmacro reg-math
  [calc]
  ; (+ 1 2) => (1 + 2)
  (list (second calc) (first calc) (nth calc 2)))

(defmacro do-more
  [cond & body]
  ; if conditions are met, executes all args
  (list `if cond (cons 'do body)))

(defmacro do-more-2
  [cond & body]
  ; Don't evaluate if, but evaluate cond
  `(if ~cond (do ~@body )))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]

  ;;;;;;;;;;;;;;;;;;;;;;;
  ; STRINGS
  ;;;;;;;;;;;;;;;;;;;

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
  ;;;;;;;;;;;;;;;;;;;

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
  ;;;;;;;;;;;;;;;;;;;

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
  ;;;;;;;;;;;;;;;;;;;

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
  ;;;;;;;;;;;;;;;;;;;

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

  ;;;;;;;;;;;;;;;;;;;;;;;;;;
  ; ATOMS
  ;;;;;;;;;;;;;;;;;;;


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

  ; 1st x 5)
  ; atomEx changed from  5  to  10
  ; 2nd x 10
  ; atomEx changed from  10  to  11
  (atom-ex 5)

  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  ; AGENTS
  ;;;;;;;;;;;;;;;;;;;

  (defn agent-ex
    []
    (def tickets-sold (agent 0))
    (send tickets-sold + 15)
    (await-for 100 tickets-sold)
    (println "Tickets sold: " @tickets-sold)

  ; shutdown agents
    (shutdown-agents))

  ; Tickets sold:  15
  (agent-ex)

  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  ; FUNCTIONS
  ;;;;;;;;;;;;;;;;;;;

  (println "\nFunctions:")

  (defn say-hello
    "Receives a name with 1 parameter and responds"
    [name]
    (println "Hello again" name "!"))

  (say-hello "Simeon")

  (defn get-sum
    "Sums 2 numbers"
    [x y]
    (+ x y))

  ; 3
  (println (get-sum 1 2))

  ; Returns a sum depending of the n of args
  (defn get-sum-more
    ([x y z]
     (+ x y z))

    ([x y]
     (+ x y)))

  ; 3
  (println (get-sum-more 1 1 1))

  ; 2
  (println (get-sum-more 1 1))

  (defn hello-you
    [name]
    (str "Hello " name))

  ; Hello Me!
  (println (hello-you "Me!"))

  (defn hello-all
    [& names]
    (map hello-you names))

  ; (Hello Doug Hello Mary Hello James)
  (println (hello-all "Doug" "Mary" "James"))

  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  ; LOGICAL (and or not) & RELATIONAL (= not= <) OPERATORS
  ;;;;;;;;;;;;;;;;;;;

  (println "\nConditionals:")

  (defn can-vote
    [age]
    (if (>= age 18)
      (println "You can vote!")
      ; else
      (println "You can't vote :(")))

  ; You can vote!
  (println (can-vote 19))

  (defn can-do-more
    [age]
    (if (>= age 18)
      (do (println "You can drive")
          (println "You can Vote"))
      (println "You can't vote")))

  ; You can drive)
  ; You can Vote
  (println (can-do-more 18))

  (defn when-ex
    [tof] ; true or false
    (when tof
      (println "1st thing")
      (println "2nd thing")))

  (when-ex true)

  (defn what-grade
    [n]
    (cond
      (< n 6) (println "Preschool")
      (= n 6) (println "Kindergarten")
      (and (> n 6) (<= n 18)) (println (format "Go to grade %d" (- n 5)))
      :else (println "Go to College!")))

  (what-grade 18)

  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
  ; LOOPS
  ;;;;;;;;;;;;;;;;;;;

  (println "\nLoops:")

  (defn one-to-x
    [x]
    (def i (atom 1))
    (while (<= @i x)
      (do
        (println @i)

        ; swap! currentValue nextValue
        (swap! i inc))))

  (println "one-to-x")
  ; 1 2 3
  (one-to-x 3) (defn double-to-x
                 [x]
                 (dotimes [i x]
                   (println (* i 2))))

  (println "double-to-x")
  ; 0 2 4 6
  (double-to-x 4)

  (defn triple-to-x
    "Tripple everything from x to y"
    [x y]
    (loop [i x]
      (when (<= i y)
        (println (* i 3))
        (recur (+ i 1)))))

  (println "triple-to-x")
  ; 3 6 9 12 15
  (triple-to-x 1 5)

  (defn print-list
    "Prints a list of numbers"
    [& nums] ; & when receiving a list of items
    (doseq [x nums]
      (println x)))

  (println "print-list")
  (print-list 7 8 9)

  ;;;;;;;;;;;;;;;;;;;;
  ; FILE I/O
  ;;;;;;;;;;;;;;;;;;;
  (println "\nI/O:")

  (defn write-to-file
    [file text]
    (with-open [wrtr (writer file)]
      (.write wrtr text)))

  ; write
  (write-to-file "test.txt" "this is text\n")

  (defn read-from-file
    [file]
    (try
      (println (slurp file))
      (catch Exception e (println "Error : " (.getMessage e)))))

  ; this is text
  (read-from-file "test.txt")

  ; (defn append-to-file
  ;   [file text]
  ;   ; mark append as true
  ;   (with-open [wrtr (writer file :append true)
  ;               (.write wrtr text)]))
  ;
  ; ; append
  ; (append-to-file "test.txt" "another line\n")

  (defn read-line-from-file
    [file]
    (with-open [rdr (reader file)]
      (doseq [line (line-seq rdr)]
        (println line))))

  ; read-line
  (println "read-line-from-file:")
  (read-line-from-file "test.txt")

  ;;;;;;;;;;;;;;;;;;;;
  ; DESTRUCTURING
  ;;;;;;;;;;;;;;;;;;;
  (println "\nDestruct:")

  (defn destruct
    []
    (def vectVals [1 2 3 4])
    (let [[one two & the-rest] vectVals] ; get the rest from vectVals
      ; w/o apply => 1 2 (3 4)
      (apply println one two the-rest)))

  ; 1 2 3 4
  (destruct)

  ;;;;;;;;;;;;;;;;;;;;
  ; STRUCT MAPS
  ;;;;;;;;;;;;;;;;;;;
  (println "\nStruct Maps:")

  (defn struct-map-ex
    []
    (defstruct Customer :Name :Phone)
    (def cust1 (struct Customer "Doug" "0401234567"))
    (def cust2 (struct-map Customer :Name "Sally" :Phone "0504567890"))
    (println "cust1:" cust1)
    (println "cust2:" (:Name cust2)))

  ; cust1: {:Name Doug, :Phone 0401234567})
  ; cust2: Sally
  (struct-map-ex)

  ;;;;;;;;;;;;;;;;;;;;
  ; ANONYMOUS FUNCTIONS
  ;;;;;;;;;;;;;;;;;;;
  (println "\nAnonymous functions:")

  ; (range 1 to 4) == [1 2 3]
  (println
   (map (fn [x] (* x x)) (range 1 4)))
  ; => (1 4 9)

  ; Compact Anonymous Function
  ; the number of args depends on how many % you have in the body
  (println
   (map #(* % 3) (range 1 4)))
  ; => (3 6 9)

  (println
   (#(* %1 %2) 2 3))
  ; => 6


  ;;;;;;;;;;;;;;;;;;;;
  ; Clojures
  ;;;;;;;;;;;;;;;;;;;
  (println "\nClojures:"

  ; Used to return custom functions

  ; define custom function
           (defn custom-multiplier
             [mult-by]
             #(* % mult-by)))

  ; Assign
  (def mult-by-4 (custom-multiplier 4))

  ; Call
  (println (mult-by-4 2))

  ;;;;;;;;;;;;;;;;;;;;
  ; Filtering Lists
  ;;;;;;;;;;;;;;;;;;;
  (println "\nFiltering Lists:")

  (println
   (take 2 [1 2 3])) ; (1 2)

  (println
   (drop 2 [1 2 3])) ; (3)

  ; Removes negs only until sees positive
  (println
   (take-while neg? [-1 0 66 -18])) ; (-1)

  (println
   (drop-while neg? [-2 -1 1 2 -3 4])) ; (1 2 -3 4)

  ; Print everything > 2
  (println
   (filter #(> % 2) [1 2 3 4])) ; (3 4)

  ;;;;;;;;;;;;;;;;;;;;
  ; Macros
  ;;;;;;;;;;;;;;;;;;;
  (println "\nMacros:")

  ; if over 65, get 10% OFF
  (discount (> 25 65) (println "10% off")
            (println "Full Price"))

  (println (reg-math (1 + 2)))

  (do-more (< 1 2 ) (println "Hello")
           (println "Hello again"))

  (do-more-2 (< 1 2)
             (println "hello-2")
             (println "again-2")))
