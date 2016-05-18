(ns sql.builder
  (:require [boot.core :as core]
            [boot.task.built-in :as task]
            [clojure.java.io :as io]
            [clojure.string :as cstr]
            [clojure.tools.logging :as log])
  (:import [net.sf.jsqlparser.util SelectUtils TablesNamesFinder]
           [net.sf.jsqlparser.schema Table Column]
           [net.sf.jsqlparser JSQLParserException]
           [net.sf.jsqlparser parser.CCJSqlParserUtil]
           [net.sf.jsqlparser.statement.select Limit]
           [net.sf.jsqlparser.expression.operators.relational EqualsTo]))

(defn select-query
  "Build a SELECT SQL query"
  [cols table]
  (let [t (Table. table)
        c (into-array String cols)]
    (SelectUtils/buildSelectFromTableAndExpressions t c)))

(defn add-where
  "Add WHERE clause to a SQL query"
  [query where-clause]
  (let [prefix "select * from t where "
        where-clause (cstr/replace where-clause #"where" "")
        where-part (-> (CCJSqlParserUtil/parse (str prefix where-clause))
                       (.getSelectBody)
                       (.getWhere))]
    (-> (.getSelectBody query)
        (.setWhere where-part))
    query))

(defn add-limit
  "Add LIMIT clause to a SQL query"
  [query limit offset]
  (let [limit-part (Limit.)]
    (.setRowCount limit-part (long limit))
    (.setOffset limit-part (long offset))
    (-> (.getSelectBody query)
        (.setLimit limit-part))
    query))

(defn add-join
  "Add JOIN query to a SQL query"
  [query table join-type left-col right-col]
  (let [t (Table. table)
        join (EqualsTo.)
        table-finder (TablesNamesFinder.)
        tables-in-query (.getTableList table-finder query)]
    (.setRightExpression join (Column. t (name right-col)))
    (.setLeftExpression join (Column. (name left-col)))
    (let [add-join (SelectUtils/addJoin query t join)]
      (case join-type
        :left  (.setLeft  add-join true)
        :right (.setRight add-join true)
        :inner (.setInner add-join true)
        :outer (.setOuter add-join true)
        (throw (JSQLParserException. (str "JOIN TYPE: " join-type " is not supported."))))
      query)))

(defn add-group-by
  "Add GROUP BY clause to a SQL query"
  [query cols]
  (doseq [col cols]
    (SelectUtils/addGroupBy query (Column. col)))
  query)
