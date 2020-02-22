# java-calc

![Java CI](https://github.com/Wiles/java-calc/workflows/Java%20CI/badge.svg) [![codecov](https://codecov.io/gh/Wiles/java-calc/branch/master/graph/badge.svg)](https://codecov.io/gh/Wiles/java-calc)

To refamiliarize myself with Java tools I decided to port my old [C++ infix notation parser](https://github.com/Wiles/win_calc/tree/master/calc_base/calc_base) to Java. Supports parenthesis, addition, subtraction, multiplication and division. Originally based on an excersice from Bjarne Stroustrup's *Programming: Principles and Practice Using C++*.

## prerequisites

* Java 11
* Maven 3

## build

From `<project_dir>/calc` run `mvn package`.

## run

From `<project_dir>/calc` run `java -jar target/calc-1.0-SNAPSHOT.jar [expression...]`

Example use:

```
> java -jar target/calc-1.0-SNAPSHOT.jar 1+1 "3*7" "5(2+3)"
1+1 = 2
3*7 = 21
5(2+3) = 25
```
