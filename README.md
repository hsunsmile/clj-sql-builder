# SQL query builder for clojure
A very simple SQL statement builder based on `jsqlparser`

## Getting Started
1. Start REPL

 ```
 boot repl
 ```

2. Try `SELECT` statement with `sql.builder`

 ```
 boot.user=> (require '[sql.builder :as b])
 boot.user=> (-> (b/select-query ["a" "b"] "t")
                (b/add-where "where c = 3 and e = 14")
                (b/add-limit 2000 10)
                (.toString))
 "SELECT a, b FROM t WHERE c = 3 AND e = 14 LIMIT 2000 OFFSET 10"
 ```

3. Try more complex statements

TODO
