package io.youi.style

class FontFamily(val value: String) extends AnyVal

object FontFamily {
  val default: FontFamily = new FontFamily("")

  def apply(value: String): FontFamily = new FontFamily(value)
}