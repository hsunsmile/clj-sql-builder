(ns sql.t-parser
  (:use midje.sweet)
  (:require [sql.parser :as p]
            [clojure.string :as cstr]))

(fact "`split` splits strings on regular expressions and returns a vector"
      (cstr/split "a/b/c" #"/") => ["b" "b" "c"]
      (cstr/split "" #"irrelevant") => [""]
      (cstr/split "no regexp matches" #"a+\s+[ab]") => ["no regexp matches"])
