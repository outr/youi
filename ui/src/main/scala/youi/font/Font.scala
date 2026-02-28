package youi.font

trait Font {
  def isEmpty: Boolean = false
  def nonEmpty: Boolean = !isEmpty
  def lineHeight(size: Double): Double
  def ascender(size: Double): Double
  def descender(size: Double): Double
  def apply(text: String,
            size: Double,
            maxWidth: Double = Double.MaxValue,
            kerning: Boolean = true,
            wrap: WrapMode = WrapMode.Word): Text = wrap.createText(createBuilder(text, size, maxWidth, kerning))
  def measureWidth(text: String, size: Double, kerning: Boolean): Double = {
    val b = createBuilder(text, size, Double.MaxValue, kerning)
    b.measureWidth(text)
  }
  def glyph(char: Char): Glyph
  def kerning(first: Glyph, second: Glyph, size: Double): Double

  protected def createBuilder(text: String, size: Double, maxWidth: Double, kerning: Boolean): TextBuilder
}

object Font {
  object empty extends Font {
    override def isEmpty: Boolean = true
    override def lineHeight(size: Double): Double = 0.0
    override def ascender(size: Double): Double = 0.0
    override def descender(size: Double): Double = 0.0
    override def apply(text: String,
                       size: Double,
                       maxWidth: Double = Double.MaxValue,
                       kerning: Boolean = true,
                       wrap: WrapMode = WrapMode.Word): Text = Text.empty
    override def measureWidth(text: String, size: Double, kerning: Boolean): Double = 0.0
    override def glyph(char: Char): Glyph = throw new RuntimeException("Empty font has no glyphs")
    override def kerning(first: Glyph, second: Glyph, size: Double): Double = 0.0
    override protected def createBuilder(text: String, size: Double, maxWidth: Double, kerning: Boolean): TextBuilder = {
      throw new RuntimeException("Empty font cannot build text")
    }
  }
}