(set-env!
  :source-paths #{"resources"}
  :resource-paths #{"src"}
  :dependencies '[[com.github.jsqlparser/jsqlparser "0.9.5"]
                  [org.clojure/tools.logging "0.3.1"]
                  [adzerk/bootlaces "0.1.13"]])

(task-options!
  pom {:project 'clj-sql-builder
       :version "0.0.2"})

(require '[sql.parser :refer :all])
(require '[adzerk.bootlaces :refer :all])
