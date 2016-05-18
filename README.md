# SQL query builder for clojure [![Build Status](https://travis-ci.org/hsunsmile/clj-sql-builder.svg?branch=master)](https://travis-ci.org/hsunsmile/clj-sql-builder) [![Clojars Project](https://img.shields.io/clojars/v/clj-sql-builder.svg)](https://clojars.org/clj-sql-builder)

A very simple SQL statement builder based on `jsqlparser`
## Dependencies
- [Build tooling for Clojure: boot-clj](http://boot-clj.com/)
 - [How to install boot](https://github.com/boot-clj/boot#install) 
- [JSqlParser: A SQL statement parser](https://github.com/JSQLParser/JSqlParser)

## Getting Started
### Syntax checker
1. Adding `clj-sql-builder` as dependency and refer to the parser task
 ```
 (set-env!
   :dependencies '[[clj-sql-builder "0.0.2"]])

 (require '[sql.parser :refer :all])
 ```
2. Run the checker
 ```
 boot parse-directory -d .
 ```
 
### SQL statement builder
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
