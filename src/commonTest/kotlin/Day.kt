package dev.petuska.aoc2021.engine

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

data class Input<O>(val id: Int, val part1Output: O, val part2Output: O)

typealias Data<O> = Pair<Int, List<Input<O>>>

typealias DataProvider<O> = () -> Data<O>

abstract class Day<O>(inputsProvider: DataProvider<O>) {
  private val data: Data<O> by lazy(inputsProvider)
  private val day: Int by lazy { data.first }
  private val inputs: List<Input<O>> by lazy { data.second }

  abstract fun partOne(inputLines: List<String>): O
  abstract fun partTwo(inputLines: List<String>): O

  @Test
  fun part1() {
    runTests(::partOne, true)
  }

  @Test
  fun part2() {
    runTests(::partTwo, false)
  }

  @OptIn(ExperimentalTime::class)
  private fun runTests(solution: (List<String>) -> O, isPartOne: Boolean) {
    val part = if (isPartOne) "One" else "Two"
    val failures =
        inputs
            .mapIndexed { i, input ->
              runCatching {
                val inputLines = loadSample(day, input.id)
                var answer: O
                val output =
                    if (isPartOne) {
                      input.part1Output
                    } else {
                      input.part2Output
                    }
                Triple(measureTime { answer = solution(inputLines) }, answer, answer == output)
              }
            }
            .filterIndexed { i, result ->
              val id = "${this::class.simpleName}:Part$part #${i + 1}"
              if (result.isSuccess) {
                val (time, answer, success) = result.getOrThrow()
                val duration =
                    time.toComponents { seconds, nanoseconds ->
                      "${seconds}s ${nanoseconds / 1_000_000}ms ${nanoseconds % 1_000_000}ns"
                    }
                if (success) {
                  println("SUCCESS [$id] $duration - Answer [$answer] is correct")
                } else {
                  println("FAILURE [$id] $duration - Answer [$answer] is incorrect")
                }
              } else {
                println("ERROR [$id] ${result.exceptionOrNull()?.message}")
                result.exceptionOrNull()?.printStackTrace()
              }
              result.getOrNull()?.third ?: result.isFailure
            }

    assertEquals(0, failures.size, "Failing test cases")
  }
}
