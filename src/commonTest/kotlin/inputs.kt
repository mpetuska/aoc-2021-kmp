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
            Output(partOne = 4512, partTwo = 1924),
            Output(partOne = 5685, partTwo = 21070)))
val day5Input: DataProvider<Int> =
    dayInput(
        5,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
val day6Input: DataProvider<Int> =
    dayInput(
        6,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
val day7Input: DataProvider<Int> =
    dayInput(
        7,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
val day8Input: DataProvider<Int> =
    dayInput(
        8,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
val day9Input: DataProvider<Int> =
    dayInput(
        9,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
val day10Input: DataProvider<Int> =
    dayInput(
        10,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
val day11Input: DataProvider<Int> =
    dayInput(
        11,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
val day12Input: DataProvider<Int> =
    dayInput(
        12,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
val day13Input: DataProvider<Int> =
    dayInput(
        13,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
val day14Input: DataProvider<Int> =
    dayInput(
        14,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
val day15Input: DataProvider<Int> =
    dayInput(
        15,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
val day16Input: DataProvider<Int> =
    dayInput(
        16,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
val day17Input: DataProvider<Int> =
    dayInput(
        17,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
val day18Input: DataProvider<Int> =
    dayInput(
        18,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
val day19Input: DataProvider<Int> =
    dayInput(
        19,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
val day20Input: DataProvider<Int> =
    dayInput(
        20,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
val day21Input: DataProvider<Int> =
    dayInput(
        21,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
val day22Input: DataProvider<Int> =
    dayInput(
        22,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
val day23Input: DataProvider<Int> =
    dayInput(
        23,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
val day24Input: DataProvider<Int> =
    dayInput(
        24,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
val day25Input: DataProvider<Int> =
    dayInput(
        25,
        listOf(
            Output(partOne = UNSOLVED, partTwo = UNSOLVED),
            Output(partOne = UNSOLVED, partTwo = UNSOLVED)))
