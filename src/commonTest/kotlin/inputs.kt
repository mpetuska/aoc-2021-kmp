package dev.petuska.aoc2021.engine

private data class Output<O>(val partOne: O, val partTwo: O)

private fun <O> dayInput(day: Int, outputs: List<Output<O>>): DataProvider<O> {
  return {
    day to
        outputs.mapIndexed { i, (ptOne, ptTwo) ->
          Input(id = i + 1, part1Output = ptOne, part2Output = ptTwo)
        }
  }
}

private const val UNSOLVED = -1

val day1Input: DataProvider<Int> =
    dayInput(1, listOf(Output(partOne = 7, partTwo = 5), Output(partOne = 1722, partTwo = 1748)))
val day2Input: DataProvider<Int> =
    dayInput(
        2,
        listOf(
            Output(partOne = 150, partTwo = 900), Output(partOne = 2120749, partTwo = 2138382217)))
val day3Input: DataProvider<Int> =
    dayInput(
        3,
        listOf(Output(partOne = 198, partTwo = 230), Output(partOne = 3959450, partTwo = 7440311)))
val day4Input: DataProvider<Int> =
    dayInput(
        4,
        listOf(
            Output(partOne = 4512, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
