#!/usr/bin/env -S kotlinc -script
import java.io.File

repeat(25) { i ->
  val day = i + 1
  val parent = File("day$day").also(File::mkdirs)
  repeat(2) {
    val input = it + 1
    File(parent, "$input.in").writeText("")
    File(parent, "$input.out").writeText("")
  }
}
