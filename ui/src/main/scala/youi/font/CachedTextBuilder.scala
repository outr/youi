package youi.font

class CachedTextBuilder(font: CachedFont,
                        text: String,
                        size: Double,
                        maxWidth: Double,
                        kerning: Boolean) extends TextBuilder(font, text, size, maxWidth, kerning) {
  override def toText: Text = CachedText(font, text, size, maxWidth, kerning, lines.reverse.map(_.reverse.toVector).toVector)
}
