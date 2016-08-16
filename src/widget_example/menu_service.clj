(ns widget-example.menu-service
  (:require [clojure.string :as str]))

; magic numbers
(def create-widget 1)
(def list-widget 2)
(def view-widget 3)
(def edit-widget 4)
(def delete-widget 5)
(def quit 6)

(defn parse-int [s]
  (Integer. (re-find #"[0-9]*" s)))

(defn wait-for-int
  ([message min max]
   (println message)
   (try
     (let [value (parse-int (read-line))]
       (if (not (and (>= value min) (<= value max)))
         (throw (Exception. "Invalid option."))
         value))
     (catch Exception e
       (do
         (println (str "\nPlease provide a number between " min " and " max ".\n"))
         (wait-for-int message min max)))))
  ([message]
   (println message)
   (try
     (parse-int (read-line))
     (catch Exception e
       (do
         (println (str "\nPlease provide a number.\n"))
         (wait-for-int message))))))

(defn wait-for-string [message required]
  (println message)
  (let [value (str/trim (read-line))]
    (if (and required (= (count value) 0))
      (do
        (println "\nPlease provide a value.\n")
        (wait-for-string message require))
      value)))

(defn prompt-for-main-menu-selection []
  (println "\n-- Main Menu --\n"
           "\n"
           "1) Create a Widget\n"
           "2) List Widgets\n"
           "3) View a Widget\n"
           "4) Edit a Widget\n"
           "5) Delete a Widget\n"
           "6) Quit\n")
  (wait-for-int "Please choose an option:", 1, 6))

(defn prompt-for-widget-data []
  (println "\n-- Create a Widget --\n")
  {
   :name (wait-for-string "Name: " true)
   :width (wait-for-string "Width: " true)
   :height (wait-for-string "Height: " true)
   :length (wait-for-string "Length: " true)
   :weight (wait-for-string "Weight: " true)})

(defn display-widgets [widgets]
  (println "\n-- List Widgets --\n")
  (println widgets))





