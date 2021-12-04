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
    val idStr = { id: Int -> "${this::class.simpleName}:Part$part #$id" }
    val failures =
        inputs
            .mapIndexed { i, input ->
              val id = idStr(i + 1)
              runCatching {
                val inputLines = loadSample(day, input.id)
                var answer: O
                measureTime { answer = solution(inputLines) }.also {
                  val output =
                      if (isPartOne) {
                        input.part1Output
                      } else {
                        input.part2Output
                      }
                  if (output != answer) error("ERROR [$id] Answer [$answer] is incorrect")
                }
              }
            }
            .filterIndexed { i, result ->
              val id = idStr(i + 1)
              if (result.isSuccess) {
                val duration =
                    result.getOrNull()?.toComponents { seconds, nanoseconds ->
                      "${seconds}s ${nanoseconds / 1_000_000}ms ${nanoseconds % 1_000_000}ns"
                    }
                println("SUCCESS [$id] $duration")
              } else {
                println("FAILURE [$id] ${result.exceptionOrNull()?.message}")
              }
              result.isFailure
            }

    failures.forEach { it.exceptionOrNull()?.printStackTrace() }
    assertEquals(0, failures.size, "Failing test cases")
  }
}
