package io.youi.font

trait Font {
  def isEmpty: Boolean = false
  def nonEmpty: Boolean = !isEmpty
  def lineHeight(size: Double): Double
  def apply(text: String,
            size: Double,
            maxWidth: Double = Double.MaxValue,
            kerning: Boolean = true,
            wrap: WrapMode = WrapMode.Word): Text = wrap.createText(this, text, size, maxWidth, kerning)
  def glyph(char: Char): Glyph
  def kerning(first: Glyph, second: Glyph, size: Double): Double
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
    override def glyph(char: Char): Glyph = throw new RuntimeException("Empty font has no glyphs")
    override def kerning(first: Glyph, second: Glyph, size: Double): Double = 0.0
  }
}