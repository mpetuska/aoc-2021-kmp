package dev.petuska.aoc.engine

import dev.petuska.klip.core.Klippable
import dev.petuska.klip.core.ext.File
import dev.petuska.klip.core.int.KlipContext
import dev.petuska.klip.core.validate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime


abstract class Day(private val day: Int, private val inputCount: Int = 2) {
  abstract fun partOne(inputLines: List<String>): Int
  abstract fun partTwo(inputLines: List<String>): Int
  
  @Test
  fun part1() {
    runTests(::partOne, true)
  }
  
  @Test
  fun part2() {
    runTests(::partTwo, false)
  }
  
  @Klippable
  private fun loadTestData(day: Int, id: Int, _context: KlipContext? = null): DataLoader.Data {
    _context.validate()
    var file: File? = File(_context.path)
    while (file?.getPath()?.endsWith("engine") == false) {
      file = file.getParentFile()
    }
    return file?.getParentFile()?.let(::DataLoader)?.loadData(day, id) ?: error("Input directory [day=$day] not found")
  }
  
  @OptIn(ExperimentalTime::class)
  private fun test(id: String, solution: (List<String>) -> Int, input: List<String>, output: Int?) {
    var result: Result<Int>
    val time = measureTime {
      result = runCatching {
        solution(input)
      }
    }
    val duration =
      time.toComponents { seconds, nanoseconds ->
        "${seconds}s ${nanoseconds / 1_000_000}ms ${nanoseconds % 1_000_000}ns"
      }
    result.onSuccess { answer ->
      when (output) {
        null -> println(AnsiColor.Blue, "INFO [$id] $duration - Answer [$answer]")
        answer -> println(AnsiColor.Green, "SUCCESS [$id] $duration - Answer [$answer] is correct")
        else -> println(AnsiColor.Orange, "FAILURE [$id] $duration - Answer [$answer] is incorrect")
      }
    }.recoverCatching {
      if (it is NotImplementedError) {
        println(AnsiColor.Blue, "INFO [$id] $duration - ${it.message}")
      } else {
        val message = "ERROR [$id] $duration - $it"
        println(AnsiColor.Red, message)
        throw IllegalStateException(message, it)
      }
    }.getOrThrow()
  }
  
  @OptIn(ExperimentalTime::class)
  private fun runTests(solution: (List<String>) -> Int, isPartOne: Boolean) {
    val part = if (isPartOne) "One" else "Two"
    val failures = List(inputCount) { it + 1 }.map { inputId ->
      val id = "${this::class.simpleName}:Part$part #$inputId"
      runCatching {
        val data = loadTestData(day, inputId)
        val output =
          if (isPartOne) {
            data.part1Output
          } else {
            data.part2Output
          }
        test(id, solution, data.input, output)
      }
    }
      .mapNotNull { result ->
        result.exceptionOrNull()
      }
    failures.forEach(Throwable::printStackTrace)
    
    assertEquals(0, failures.size, "Failing test cases")
  }
}
