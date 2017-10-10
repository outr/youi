package io.youi.font

trait Font {
  def lineHeight(size: Double): Double
  def apply(text: String,
            size: Double,
            maxWidth: Double = Double.MaxValue,
            kerning: Boolean = true): Text
}

object Font {
  object empty extends Font {
    override def lineHeight(size: Double): Double = 0.0
    override def apply(text: String,
                       size: Double,
                       maxWidth: Double = Double.MaxValue,
                       kerning: Boolean = true): Text = Text.empty
  }
}