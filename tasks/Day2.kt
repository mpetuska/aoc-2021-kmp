import dev.petuska.aoc2021.engine.Day
import dev.petuska.aoc2021.engine.day2Input

/** [The Task](https://adventofcode.com/2021/day/2) */
object Day2 : Day<Int>(day2Input) {
  override fun partOne(inputLines: List<String>): Int {
    val commands = inputLines.map { it.split(" ") }.map { it[0] to it[1].toInt() }
    
    return commands
        .fold(0 to 0) { (x, y), (direction, steps) ->
          when (direction) {
            "up" -> x to y - steps
            "down" -> x to y + steps
            "forward" -> x + steps to y
            else -> error("Unknown direction - $direction")
          }
        }
        .let { (x, y) -> x * y }
  }

  override fun partTwo(inputLines: List<String>): Int {
    val commands = inputLines.map { it.split(" ") }.map { it[0] to it[1].toInt() }
    
    return commands
        .fold(Triple(0, 0, 0)) { (x, y, z), (direction, steps) ->
          when (direction) {
            "up" -> Triple(x, y, z - steps)
            "down" -> Triple(x, y, z + steps)
            "forward" -> Triple(x + steps, y + z * steps, z)
            else -> error("Unknown direction - $direction")
          }
        }
        .let { it.first * it.second }
  }
}
