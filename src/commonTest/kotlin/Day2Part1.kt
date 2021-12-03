package dev.petuska.aoc2021

import dev.petuska.aoc2021.engine.HalfDay
import dev.petuska.aoc2021.engine.day2Inputs1

/** [The Task](https://adventofcode.com/2021/day/2) */
object Day2Part1 : HalfDay<List<Day2Part1.Command>, Int>(day2Inputs1) {
  override fun solution(commands: List<Command>): Int {
    return TODO()
  }
  
  data class Command(val direction: Direction, val steps: Int) {
    @Suppress("EnumEntryName")
    enum class Direction {
      up,
      down,
      forward
    }
  }
}
