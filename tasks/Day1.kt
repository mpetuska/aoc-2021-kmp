import dev.petuska.aoc2021.engine.Day
import dev.petuska.aoc2021.engine.day1Input

/** [The Task](https://adventofcode.com/2021/day/1) */
object Day1 : Day<Int>(day1Input) {
  override fun partOne(inputLines: List<String>): Int {
    val measurements = inputLines.map(String::toInt)
    
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

  override fun partTwo(inputLines: List<String>): Int {
    val measurements = inputLines.map(String::toInt)
    
    return measurements
      .windowed(3)
      .windowed(2) {
        if (it[0].sum() < it[1].sum()) {
          1
        } else {
          0
        }
      }
      .sum()
  }
}
