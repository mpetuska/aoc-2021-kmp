package dev.petuska.aoc2021.engine

import kotlin.jvm.JvmInline

@JvmInline
value class AnsiColor private constructor(private val value: String) {
  private constructor(code: Int) : this(PREFIX + code + SUFFIX)
  private constructor(
      red: Int,
      green: Int,
      blue: Int
  ) : this(PREFIX + "38;2;$red;$green;$blue" + SUFFIX)
  override fun toString(): String = value

  // https://chrisyeh96.github.io/2020/03/28/terminal-colors.html
  companion object {
    private const val PREFIX = "\u001B["
    private const val SUFFIX = "m"
    val RESET = AnsiColor(0)
    val Black = AnsiColor(30)
    val Red = AnsiColor(31)
    val Green = AnsiColor(32)
    val Yellow = AnsiColor(33)
    val Blue = AnsiColor(34)
    val Purple = AnsiColor(35)
    val Cyan = AnsiColor(36)
    val White = AnsiColor(37)
    // NON-STANDARD
    val Orange = AnsiColor(255, 165, 0)
  }
}

private val suportsAnsi = true // System.console() != null && System.getenv("TERM") != null

val println: (color: AnsiColor, message: Any?) -> Unit =
    if (suportsAnsi) ({ color, message -> println("$color$message${AnsiColor.RESET}") })
    else ({ _, message -> println(message) })
