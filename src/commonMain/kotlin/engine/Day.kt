package dev.petuska.aoc2021.engine

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

data class Input<O>(val inputLines: List<String>, val part1Output: O, val part2Output: O)

typealias Inputs<O> = List<Input<O>>

typealias InputsProvider<O> = () -> Inputs<O>

abstract class Day<O>(testCasesProvider: InputsProvider<O>) {
  private val testCases: Inputs<O> by lazy(testCasesProvider)

  abstract fun partOne(inputLines: List<String>): O
  abstract fun partTwo(inputLines: List<String>): O

  @Test
  fun runPartOne() {
    runTests(::partOne, Input<O>::part1Output)
  }

  @Test
  fun runPartTwo() {
    runTests(::partTwo, Input<O>::part2Output)
  }

  @OptIn(ExperimentalTime::class)
  private fun runTests(solution: (List<String>) -> O, output: Input<O>.() -> O) {
    val failures =
        testCases
            .map { input ->
              runCatching {
                var answer: O
                measureTime { answer = solution(input.inputLines) }.also {
                  if (input.output() != answer) error("Answer [$answer] is incorrect")
                }
              }
            }
            .filterIndexed { index, result ->
              if (result.isSuccess) {
                val duration =
                    result.getOrNull()?.toComponents { seconds, nanoseconds ->
                      "${seconds}s ${nanoseconds / 1_000_000}ms ${nanoseconds % 1_000_000}ns"
                    }
                println("SUCCESS [${this::class.simpleName} #$index] $duration")
              } else {
                println(
                    "FAILURE [${this::class.simpleName} #$index] ${result.exceptionOrNull()?.message}")
                result.exceptionOrNull()?.printStackTrace()
              }
              result.isFailure
            }

    assertEquals(0, failures.size, "Failing test cases")
  }
}
