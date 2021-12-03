package dev.petuska.aoc2021

import dev.petuska.aoc2021.engine.HalfDay
import dev.petuska.aoc2021.engine.day3Inputs1
import kotlin.math.abs

/** [The Task](https://adventofcode.com/2021/day/3) */
object Day3Part1 : HalfDay<List<List<Int>>, Int>(day3Inputs1) {
  override fun solution(reportRows: List<List<Int>>): Int {
    // No Math.pow in common stdlib
    fun Int.pow(power: Int): Int {
      return if (power == 0) {
        1
      } else {
        var result = this
        repeat(abs(power - 1)) { result *= this }
        result
      }
    }
    val width = reportRows.first().size
    val commonalityIndex = MutableList(width) { Pair(0, 0) }
    reportRows.forEach { row ->
      row.forEachIndexed { index, bit ->
        commonalityIndex[index].let { (zeroes, ones) ->
          commonalityIndex[index] =
              if (bit == 0) {
                zeroes + 1 to ones
              } else {
                zeroes to ones + 1
              }
        }
      }
    }
    fun List<Int>.decodeByte() =
        foldIndexed(0) { i, acc, bit -> acc + bit * 2.pow(width - (i + 1)) }
    val gamma = commonalityIndex.map { if (it.first > it.second) 0 else 1 }
    val epsilon = commonalityIndex.map { if (it.first < it.second) 0 else 1 }
    println("$gamma $epsilon")
    return gamma.decodeByte() * epsilon.decodeByte()
  }
}
