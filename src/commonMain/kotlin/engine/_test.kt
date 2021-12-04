package dev.petuska.aoc2021.engine

import dev.petuska.klip.core.Klippable
import dev.petuska.klip.core.ext.File
import dev.petuska.klip.core.ext.newline
import dev.petuska.klip.core.ext.readText
import dev.petuska.klip.core.ext.separator
import dev.petuska.klip.core.int.KlipContext
import dev.petuska.klip.core.validate

@Klippable
fun loadSample(day: Int, input: Int, _context: KlipContext? = null): List<String> {
  _context.validate()
  var file: File? = File(_context.path)
  while (file?.getPath()?.endsWith("commonMain") == false) {
    println(file.getPath())
    file = file.getParentFile()
  }
  val inputPath = "/resources/day$day/input$input.txt"
  val inputFilePath = file?.getPath()?.let { it + inputPath.replace("/", file.separator) }
  val inputFile = inputFilePath?.let(::File)?.takeIf(File::exists)
  return inputFile?.readText()?.split(inputFile.newline)?.filterNot(String::isBlank)
      ?: error("Input file [day=$day, input:$input, path=$inputFilePath] not found")
}
