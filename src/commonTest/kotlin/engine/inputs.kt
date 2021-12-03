package dev.petuska.aoc2021.engine

import dev.petuska.aoc2021.Day2Part1

val day1Inputs1: Map<List<Int>, Int> = dev.petuska.aoc2021.input.day1.zip(listOf(0, 1722)).toMap()
val day1Inputs2: Map<List<Int>, Int> = dev.petuska.aoc2021.input.day1.zip(listOf(5, 1748)).toMap()

private val day2InputsRaw =
    dev.petuska.aoc2021.input.day2.map {
      it.map { (c, s) -> Day2Part1.Command(Day2Part1.Command.Direction.valueOf(c), s) }
    }
val day2Inputs1: Map<List<Day2Part1.Command>, Int> = day2InputsRaw.zip(listOf(150, 2120749)).toMap()

val day2Inputs2: Map<List<Day2Part1.Command>, Int> = day2InputsRaw.zip(listOf(900, 2138382217)).toMap()
