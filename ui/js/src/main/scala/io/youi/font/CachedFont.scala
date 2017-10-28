package io.youi.font

class CachedFont(font: Font) extends Font {
  override def lineHeight(size: Double): Double = font.lineHeight(size)

  override def glyph(char: Char): Glyph = font.glyph(char)

  override def kerning(first: Glyph, second: Glyph, size: Double): Double = font.kerning(first, second, size)

  override protected def createBuilder(text: String, size: Double, maxWidth: Double, kerning: Boolean): TextBuilder = {
    new CachedTextBuilder(this, text, size, maxWidth, kerning)
  }
}
