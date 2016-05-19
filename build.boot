(set-env!
  :source-paths   #{"resources"}
  :resource-paths #{"src"}
  :dependencies '[[com.github.jsqlparser/jsqlparser "0.9.5"]
                  [org.clojure/tools.logging "0.3.1"]
                  [adzerk/bootlaces "0.1.13"]
                  [midje "1.8.3"]
                  [zilti/boot-midje "0.2.1-SNAPSHOT"]])

(deftask deps
  "Install all dependencies"
  []
  identity)

(deftask testing
  "Profile setup for running tests."
  []
  (set-env! :source-paths #{"test"})
  identity)

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
(require '[zilti.boot-midje :refer :all])
