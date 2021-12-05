#!/usr/bin/env kotlin

@JvmInline
value class AnsiColor private constructor(private val value: String) {
  private constructor(code: Int) : this("$PREFIX$code$SUFFIX")
  override fun toString(): String = value
  
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
  }
}
private val suportsAnsi = System.console() != null && System.getenv("TERM") != null

val println:(color: AnsiColor, message: Any?) -> Unit = if(suportsAnsi)
    ({ color, message ->   println("$color$message${AnsiColor.RESET}")
})
else
    ({_,message -> println(message)})
  
println(AnsiColor.Green, "In a while, crocodile")
