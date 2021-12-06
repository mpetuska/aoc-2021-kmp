# Advent of Code 2021 in Kotlin Multiplatform

## Structure

Each day comes with two parts for you to solve. You'll find a ready-to go and test templates to start coding straight
away here. All exercises live in [./tasks](./tasks) directory and come with prepopulated
structure and test cases.

## Solving

To start, pick a day (`X`) and part (`Y`), then open up its template - `DayX.kt`. Implement the solution method and run
it on JVM via `./gradlew cleanJvmTest jvmTest --tests "dev.petuska.aoc2021.DayX.partY"`

e.g.: `./gradlew cleanJvmTest jvmTest --tests "dev.petuska.aoc2021.Day1.part2"`

You can also run same implementation on other platforms or ALL platforms via `./gradlew allTests`.

Test outputs come with uniform formatting and some useful info. It's also quite nice to compare how the same
implementation performs under different runtimes by their execution time printed in the output.

```text
SUCCESS [Day2:PartOne #0] 0s 5ms 5912us - Answer [****] is correct
FAILURE [Day2:PartOne #1] 0s 5ms 5912us - Answer [****] is incorrect
```

## Branching

The repository comes with two git branches

* `unsolved` (default) - only the tasks with the test framework and hidden test inputs. No solutions or spoilers
* `solved` - same code, but with solutions filled in for each task
