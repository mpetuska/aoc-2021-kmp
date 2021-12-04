package dev.petuska.aoc2021.input

import dev.petuska.aoc2021.engine.Input
import dev.petuska.aoc2021.engine.InputsProvider
import dev.petuska.aoc2021.engine.loadSample

private fun <O> dayInput(day: Int, outputs: List<Pair<O, O>>): InputsProvider<O> {
  return {
    outputs.mapIndexed { i, (ptOne, ptTwo) ->
      Input(inputLines = loadSample(day, input = i + 1), part1Output = ptOne, part2Output = ptTwo)
    }
  }
}

private const val UNSOLVED = -1

val day1Input: InputsProvider<Int> = dayInput(1, listOf(0 to 1722, 5 to 1748))
val day2Input: InputsProvider<Int> = dayInput(2, listOf(150 to 2120749, 900 to 2138382217))
val day3Input: InputsProvider<Int> = dayInput(3, listOf(198 to 3959450, 230 to 7440311))
val day4Input: InputsProvider<Int> = dayInput(4, listOf(4512 to UNSOLVED, UNSOLVED to UNSOLVED))
