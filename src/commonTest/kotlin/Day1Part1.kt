package dev.petuska.aoc2021

import dev.petuska.aoc2021.engine.HalfDay
import dev.petuska.aoc2021.engine.day1Inputs1

/** [The Task](https://adventofcode.com/2021/day/1) */
object Day1Part1 : HalfDay<List<Int>, Int>(day1Inputs1) {
  override fun solution(measurements: List<Int>): Int {
    return measurements
      .windowed(2) {
        if (it[0] < it[1]) {
          1
        } else {
          0
        }
      }
      .sum()
  }
}
