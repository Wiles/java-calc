# java-calc

[![CircleCI](https://circleci.com/gh/Wiles/java-calc/tree/master.svg?style=svg&circle-token=07583b7d6ca35e7d245c8c4527e0e0dc32473e08)](https://circleci.com/gh/Wiles/java-calc/tree/master) [![Build Status](https://travis-ci.com/Wiles/java-calc.svg?token=h6vde4sb93o2xMugTuif&branch=master)](https://travis-ci.com/Wiles/java-calc)

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

## coverage

JaCoCo overage site output to `<project_dir>/calc/target/site/jacoco/index.html`
