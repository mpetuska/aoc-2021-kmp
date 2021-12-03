package dev.petuska.aoc2021.engine

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

abstract class HalfDay<I, O>(private val testCases: Map<I, O>) {
  @Test
  @OptIn(ExperimentalTime::class)
  fun runTests() {
    val failureCount =
        testCases
            .map { (i, o) ->
              runCatching {
                var answer: O
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
                println("SUCCESS [${this::class.simpleName} #$i] $duration")
              } else {
                println(
                    "FAILURE [${this::class.simpleName} #$i] ${result.exceptionOrNull()?.message}")
                result.exceptionOrNull()?.printStackTrace()
              }
              result.isFailure
            }
    assertEquals(0, failureCount.size, "Failing test cases")
  }
  abstract fun solution(inputs: I): O
}
