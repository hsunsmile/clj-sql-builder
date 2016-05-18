(ns sql.parser
  (:require [boot.core :as core]
            [boot.task.built-in :as task]
            [clojure.java.io :as io]
            [clojure.tools.logging :as log])
  (:import [net.sf.jsqlparser.parser CCJSqlParserUtil]))

(defn parse-query
  "Parse a SQL query"
  [query]
  (CCJSqlParserUtil/parse query))

(core/deftask parse-directory
  "Parse SQL queries in a directory"
  [d dir DIR kw "Directory to scan."]
  (let [target-dir (name (or dir "."))
        sqls (->> (io/file target-dir)
                  file-seq
                  (filter #(.endsWith (.getName %) ".sql")))]
    (log/info (str "scanning directory for sql files: " target-dir))
    (doseq [sql sqls]
           (log/info (str "Checking syntax for file: " (.getName sql)))
           (parse-query (slurp sql)))))
