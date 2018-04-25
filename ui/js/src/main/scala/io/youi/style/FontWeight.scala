package io.youi.style

class FontWeight(val value: String) extends AnyVal

object FontWeight {
  val default: FontWeight = new FontWeight("")

  def apply(value: String): FontWeight = new FontWeight(value)
}