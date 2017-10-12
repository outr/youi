package io.youi.font

trait Font {
  def isEmpty: Boolean = false
  def nonEmpty: Boolean = !isEmpty
  def lineHeight(size: Double): Double
  def apply(text: String,
            size: Double,
            maxWidth: Double = Double.MaxValue,
            kerning: Boolean = true,
            wrap: WrapMode = WrapMode.Word): Text
}

object Font {
  object empty extends Font {
    override def isEmpty: Boolean = true
    override def lineHeight(size: Double): Double = 0.0
    override def apply(text: String,
                       size: Double,
                       maxWidth: Double = Double.MaxValue,
                       kerning: Boolean = true,
                       wrap: WrapMode = WrapMode.Word): Text = Text.empty
  }
}