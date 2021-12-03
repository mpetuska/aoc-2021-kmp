package dev.petuska.aoc2021

import dev.petuska.aoc2021.engine.HalfDay
import dev.petuska.aoc2021.engine.day3Inputs2
import kotlin.math.abs

/** [The Task](https://adventofcode.com/2021/day/3) */
object Day3Part2 : HalfDay<List<List<Int>>, Int>(day3Inputs2) {
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
    fun List<Int>.decodeByte() =
        foldIndexed(0) { i, acc, bit -> acc + bit * 2.pow(width - (i + 1)) }

    tailrec fun List<List<Int>>.reduceToSingle(
        i: Int = 0,
        selectBit: (zeros: Int, ones: Int) -> Int,
    ): List<Int> =
        if (size == 1) {
          first()
        } else {
          val zeros = count { it[i] == 0 }
          val ones = size - zeros
          filter { it[i] == selectBit(zeros, ones) }.reduceToSingle(i + 1, selectBit)
        }

    val co2 = reportRows.reduceToSingle { zeros, ones -> if (zeros <= ones) 0 else 1 }
    val o2 = reportRows.reduceToSingle { zeros, ones -> if (zeros <= ones) 1 else 0 }

    println("$co2 $o2")
    return co2.decodeByte() * o2.decodeByte()
  }
}
