import dev.petuska.aoc.engine.Day
import kotlin.math.abs

/** [The Task](https://adventofcode.com/2021/day/3) */
object Day3 : Day(3) {
  override fun partOne(inputLines: List<String>): Int {
    val reportRows = inputLines.filter(String::isNotBlank).map { it.split("").mapNotNull(String::toIntOrNull) }

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

  override fun partTwo(inputLines: List<String>): Int {
    val reportRows = inputLines.filter(String::isNotBlank).map { it.split("").mapNotNull(String::toIntOrNull) }

    val co2 = reportRows.reduceToSingle { zeros, ones -> if (zeros <= ones) 0 else 1 }
    val o2 = reportRows.reduceToSingle { zeros, ones -> if (zeros <= ones) 1 else 0 }

    println("$co2 $o2")
    return co2.decodeByte() * o2.decodeByte()
  }

  // No Math.pow in common stdlib
  private fun Int.pow(power: Int): Int {
    return if (power == 0) {
      1
    } else {
      var result = this
      repeat(abs(power - 1)) { result *= this }
      result
    }
  }

  private fun List<Int>.decodeByte() =
      foldIndexed(0) { i, acc, bit -> acc + bit * 2.pow(size - (i + 1)) }

  private tailrec fun List<List<Int>>.reduceToSingle(
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
}
