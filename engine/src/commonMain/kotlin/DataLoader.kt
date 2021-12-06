package dev.petuska.aoc.engine

import dev.petuska.klip.core.ext.File
import dev.petuska.klip.core.ext.newline
import dev.petuska.klip.core.ext.readText
import dev.petuska.klip.core.ext.separator


internal class DataLoader(private val root: File) {
  data class Data(val input: List<String>, val part1Output: Int?, val part2Output: Int?)
  
  private fun find(day: Int, id: Int, ext: String): File {
    val inputFilePath = root.getPath() + "/inputs/day$day/$id.$ext".replace("/", root.separator)
    return inputFilePath.let(::File).takeIf(File::exists)
      ?: error("File [day=$day, id=$id, path=$inputFilePath] not found")
  }
  
  fun loadInput(day: Int, id: Int): List<String> {
    val inputFile = find(day, id, "in")
    return inputFile.readText().split(inputFile.newline).takeIf { it.any(String::isNotBlank) }
      ?: error("InputFile [day=$day, id=$id, path=${inputFile.getPath()}] is blank")
  }
  
  fun loadOutput(day: Int, id: Int): Pair<Int?, Int?> {
    val outputFile = find(day, id, "out")
    return outputFile.readText().split(outputFile.newline).filterNot(String::isBlank).let {
      it.getOrNull(0)?.toInt() to it.getOrNull(1)?.toInt()
    }
  }
  
  fun loadData(day: Int, id: Int): Data {
    val (ptOne, ptTwo) = loadOutput(day, id)
    return Data(loadInput(day, id), ptOne, ptTwo)
  }
}
