package dev.petuska.aoc2021

import dev.petuska.aoc2021.engine.HalfDay
import dev.petuska.aoc2021.engine.day2Inputs2

/** [The Task](https://adventofcode.com/2021/day/2) */
object Day2Part2 : HalfDay<List<Day2Part1.Command>, Int>(day2Inputs2) {
  override fun solution(commands: List<Day2Part1.Command>): Int {
    return commands
      .fold(Triple(0, 0, 0)) { (x, y, z), (direction, steps) ->
        when (direction) {
          Day2Part1.Command.Direction.up -> Triple(x, y, z - steps)
          Day2Part1.Command.Direction.down -> Triple(x, y, z + steps)
          Day2Part1.Command.Direction.forward -> Triple(x + steps, y + z * steps, z)
        }
      }
      .let { it.first * it.second }
  }
}
