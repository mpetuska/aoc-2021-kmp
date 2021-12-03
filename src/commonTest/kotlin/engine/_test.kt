package dev.petuska.aoc2021.engine

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

abstract class Day {
  interface Solution<I, O> {
    val testCases: Map<I, O>
    operator fun invoke(input: I): O

    companion object {
      operator fun <I, O> invoke(testCases: Map<I, O>, solution: (I) -> O): Solution<Any, O> =
          object : Day.Solution<Any, O> {
            override val testCases: Map<Any, O> = testCases as Map<Any, O>

            override fun invoke(input: Any): O = solution(input as I)
          }
    }
  }
  abstract val solution: Solution<Any, out Any>
  @Test
  @OptIn(ExperimentalTime::class)
  fun runTests() {
    val failureCount =
        solution.testCases
            .map { (i, o) ->
              runCatching {
                var answer: Any
                measureTime { answer = solution(i) }.also {
                  if (o != answer) error("Answer [$answer] is incorrect")
                }
              }
            }
            .filterIndexed { i, result ->
              if (result.isSuccess) {
                val duration =
                    result.getOrNull()?.let {
                      "${it.inWholeSeconds}s ${it.inWholeMilliseconds}ms ${it.inWholeMicroseconds}us"
                    }
                println("SUCCESS [${this::class.simpleName} $i] $duration")
              } else {
                println(
                    "FAILURE [${this::class.simpleName} $i] ${result.exceptionOrNull()?.message}")
              }
              result.isFailure
            }
    assertEquals(0, failureCount.size, "Failing test cases")
  }
}
