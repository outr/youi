package youi.font

class OpenTypeTextBuilder(font: Font,
                          text: String,
                          size: Double,
                          maxWidth: Double,
                          kerning: Boolean) extends TextBuilder(font, text, size, maxWidth, kerning) {
  override def toText: Text = {
    OpenTypeText(font, text, size, maxWidth, kerning, lines.reverse.map(_.reverse.toVector).toVector)
  }
}