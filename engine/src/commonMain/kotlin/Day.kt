package dev.petuska.aoc.engine

import dev.petuska.klip.core.Klippable
import dev.petuska.klip.core.ext.File
import dev.petuska.klip.core.ext.newline
import dev.petuska.klip.core.ext.readText
import dev.petuska.klip.core.ext.separator
import dev.petuska.klip.core.int.KlipContext
import dev.petuska.klip.core.validate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

data class Input<O>(val id: Int, val part1Output: O?, val part2Output: O?)

typealias Data<O> = Pair<Int, List<Input<O>>>

typealias DataProvider<O> = () -> Data<O>

abstract class Day<O>(inputsProvider: DataProvider<O>) {
  private val data: Data<O> by lazy(inputsProvider)
  private val day: Int by lazy { data.first }
  private val inputs: List<Input<O>> by lazy { data.second }
  
  abstract fun partOne(inputLines: List<String>): O
  abstract fun partTwo(inputLines: List<String>): O
  
  @Klippable
  private fun loadTestSample(day: Int, input: Int, _context: KlipContext? = null): List<String> {
    _context.validate()
    var file: File? = File(_context.path)
    while (file?.getPath()?.endsWith("engine") == false) {
      file = file.getParentFile()
    }
    val inputPath = "/inputs/day$day/input$input.txt"
    val inputFilePath = file?.getParentFile()?.getPath()?.let { it + inputPath.replace("/", file.separator) }
    val inputFile = inputFilePath?.let(::File)?.takeIf(File::exists)
    return inputFile?.readText()?.split(inputFile.newline)?.filterNot(String::isBlank)
      ?: error("Input file [day=$day, input=$input, path=$inputFilePath] not found")
  }
  
  @Test
  fun part1() {
    runTests(::partOne, true)
  }
  
  @Test
  fun part2() {
    runTests(::partTwo, false)
  }
  
  @OptIn(ExperimentalTime::class)
  private fun test(id: String, solution: (List<String>) -> O, input: List<String>, output: O?) {
    var result: Result<O>
    val duration = measureTime {
      result = runCatching {
        solution(input)
      }
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
  private fun runTests(solution: (List<String>) -> O, isPartOne: Boolean) {
    val part = if (isPartOne) "One" else "Two"
    val failures =
      inputs
        .mapIndexed { i, input ->
          val id = "${this::class.simpleName}:Part$part #${i + 1}"
          runCatching {
            val inputLines = loadTestSample(day, input.id)
            val output =
              if (isPartOne) {
                input.part1Output
              } else {
                input.part2Output
              }
            test(id, solution, inputLines, output)
          }
        }
        .mapNotNull { result ->
          result.exceptionOrNull()
        }
    failures.forEach(Throwable::printStackTrace)
    
    assertEquals(0, failures.size, "Failing test cases")
  }
}
