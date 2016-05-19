(set-env!
  :source-paths #{"resources"}
  :resource-paths #{"src"}
  :dependencies '[[com.github.jsqlparser/jsqlparser "0.9.5"]
                  [org.clojure/tools.logging "0.3.1"]
                  [adzerk/bootlaces "0.1.13"]])

(def +version+ "0.0.3")

(require '[adzerk.bootlaces :refer :all])
(bootlaces! +version+)

(task-options!
  pom {:project     'clj-sql-builder
       :version     +version+
       :description "A very simple SQL statement builder based on jsqlparser"
       :url         "https://github.com/hsunsmile/clj-sql-builder"
       :scm         {:url "https://github.com/hsunsmile/clj-sql-builder"}
       :license     {"MIT" "https://opensource.org/licenses/MIT"}})

(require '[sql.parser :refer :all])
