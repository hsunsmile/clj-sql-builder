(set-env!
  :source-paths #{"src"}
  :resource-paths #{"resources"}
  :dependencies '[[com.github.jsqlparser/jsqlparser "0.9.5"]
                  [org.clojure/tools.logging "0.3.1"]])

(require '[sql.parser :refer :all])
