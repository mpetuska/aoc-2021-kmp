import dev.petuska.aoc2021.engine.Day
import dev.petuska.aoc2021.engine.day4Input

private typealias Coordinates = Pair<Int, Int>

private typealias Value = Int

private typealias Line = List<Value>

private typealias Board = List<Line>

/** [The Task](https://adventofcode.com/2021/day/4) */
object Day4 : Day<Int>(day4Input) {
  override fun partOne(inputLines: List<String>): Int {
    val (draws, boards) = parseInput(inputLines)
    val boardIndexes = boards.map(::BoardIndex)
    val (winner, remainder) = boardIndexes.winFirst(draws)

    return winner * remainder.sum()
  }

  override fun partTwo(inputLines: List<String>): Int {
    val (draws, boards) = parseInput(inputLines)
    val boardIndexes = boards.map(::BoardIndex)
    val (winner, remainder) = boardIndexes.winLast(draws)

    println("$winner * $remainder")
    return winner * remainder.sum()
  }

  private data class BoardIndex(
      val board: Board,
      val checked: MutableMap<Value, Boolean> = mutableMapOf()
  ) {
    val rows: Board = board
    val columns: Board = List(board.size) { x -> board.map { row -> row[x] } }
    val position: Map<Value, List<Coordinates>> = buildMap {
      board.forEachIndexed { y, row ->
        row.forEachIndexed { x, value ->
          checked[value] = checked[value] ?: false
          this[value] = (this[value] ?: mutableListOf()) + Coordinates(y, x)
        }
      }
    }

    operator fun get(value: Value): Pair<Boolean, List<Coordinates>>? =
        checked[value]?.let { it to position[value]!! }
    operator fun set(value: Value, checked: Boolean) {
      this.checked[value] = checked
    }
  }

  private fun parseInput(inputLines: List<String>): Pair<List<Value>, List<Board>> {
    val draws = inputLines[0].split(",").map(String::toInt)
    val boards =
        inputLines.drop(1).filter(String::isNotBlank).chunked(5).map {
          it.map { line -> line.split(" ").filter(String::isNotBlank).map(String::toInt) }
        }
    return draws to boards
  }

  private tailrec fun List<BoardIndex>.winFirst(
      draws: List<Value>,
      drawIdx: Int = 0,
  ): Pair<Value, List<Value>> {
    val draw = draws[drawIdx]
    val selection = firstOrNull { boardIndex ->
      boardIndex[draw] = true
      boardIndex.rows.any { row -> row.all { boardIndex.checked[it] == true } } ||
          boardIndex.columns.any { row -> row.all { boardIndex.checked[it] == true } }
    }

    return if (selection != null) {
      val winningDraws = draws.take(drawIdx + 1)
      draw to selection.rows.flatMap { row -> row.filter { it !in winningDraws } }
    } else winFirst(draws, drawIdx + 1)
  }

  private tailrec fun List<BoardIndex>.winLast(
      draws: List<Value>,
      drawIdx: Int = 0,
      lastWinner: Pair<Value, List<Value>>? = null,
  ): Pair<Value, List<Value>> {
    val draw = draws[drawIdx]
    val winningBoards = filter { boardIndex ->
      boardIndex[draw] = true
      boardIndex.rows.any { row -> row.all { boardIndex.checked[it] == true } } ||
          boardIndex.columns.any { row -> row.all { boardIndex.checked[it] == true } }
    }

    val winner =
        winningBoards.firstOrNull()?.let { sel ->
          val winningDraws = draws.take(drawIdx + 1)
          draw to sel.rows.flatMap { row -> row.filter { it !in winningDraws } }
        }

    return when (drawIdx) {
      draws.size - 1 -> winner ?: lastWinner!!
      else -> filter { it !in winningBoards }.winLast(draws, drawIdx + 1, winner ?: lastWinner)
    }
  }
}
