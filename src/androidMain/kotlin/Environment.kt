package dev.petuska.aoc2021

actual object Environment {
  actual operator fun get(key: String): String? = System.getenv(key)
}
