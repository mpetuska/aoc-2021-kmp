#!/usr/bin/env kotlin

import java.io.File

val input = File("src/commonTest/kotlin/inputs.kt")
for(x in 5..25){
val content = """
  val day${x}Input: DataProvider<Int> =
  dayInput(
    $x,
    listOf(
      Output(partOne = UNSOLVED, partTwo = UNSOLVED),
      Output(partOne = UNSOLVED, partTwo = UNSOLVED)))"""
  input.appendText(content)
}
