import dev.petuska.aoc.engine.Day


/** [The Task](https://adventofcode.com/2021/day/1) */
object Day1 : Day(1) {
  override fun partOne(inputLines: List<String>): Int {
    val measurements = inputLines.filter(String::isNotBlank).map(String::toInt)
    
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
    val measurements = inputLines.filter(String::isNotBlank).map(String::toInt)
    
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
