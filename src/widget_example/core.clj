(ns widget-example.core
  (:require [widget-example.menu-service :as menu]
            [widget-example.widget-service :as service]))

(defn -main []
  (loop [widgets []]
    (let [primary-action (menu/prompt-for-main-menu-selection)]
      (cond
        (= primary-action menu/create-widget) (recur (service/create-widget (menu/prompt-for-widget-data) widgets))
        (= primary-action menu/list-widget) (do
                                              (menu/display-widgets widgets)
                                              (recur widgets))))))


