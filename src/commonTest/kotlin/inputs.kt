package dev.petuska.aoc2021.engine

private fun <O> dayInput(day: Int, outputs: List<Pair<O, O>>): DataProvider<O> {
  return {
    day to
        outputs.mapIndexed { i, (ptOne, ptTwo) ->
          Input(id = i + 1, part1Output = ptOne, part2Output = ptTwo)
        }
  }
}

private const val UNSOLVED = -1

val day1Input: DataProvider<Int> = dayInput(1, listOf(0 to 1722, 5 to 1748))
val day2Input: DataProvider<Int> = dayInput(2, listOf(150 to 2120749, 900 to 2138382217))
val day3Input: DataProvider<Int> = dayInput(3, listOf(198 to 3959450, 230 to 7440311))
val day4Input: DataProvider<Int> = dayInput(4, listOf(4512 to UNSOLVED, UNSOLVED to UNSOLVED))
