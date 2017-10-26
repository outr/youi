package io.youi.font

import scala.collection.mutable.ListBuffer

trait WrapMode {
  def createText(font: Font,
                 text: String,
                 size: Double,
                 maxWidth: Double = Double.MaxValue,
                 kerning: Boolean = true): Text = {
    var offsetX = 0.0
    var offsetY = 0.0
    val lineHeight = font.lineHeight(size)
    var previous: Option[Glyph] = None
    val line = ListBuffer.empty[CharacterPath]
    val lines = ListBuffer.empty[Vector[CharacterPath]]

    def processCharacters(chars: List[Char], index: Int = 0): Unit = if (chars.nonEmpty) {
      val char = chars.head
      if (char == '\n') {
        offsetX = 0.0
        offsetY += lineHeight
        previous = None
        lines += line.toVector
        line.clear()
      } else {
        val glyph = font.glyph(char)
        var kernOffset = if (kerning && previous.nonEmpty) {
          font.kerning(previous.get, glyph, size)
        } else {
          0.0
        }
        previous = Some(glyph)
        if (offsetX + kernOffset + glyph.width(size) > maxWidth && line.nonEmpty) {
          offsetX = 0.0
          offsetY += lineHeight
          previous = None
          kernOffset = 0.0
          lines += line.toVector
          line.clear()
        }
        line += CharacterPath(char, size, index, offsetX + kernOffset, offsetY, glyph)
        offsetX += glyph.width(size)
        offsetX += kernOffset
      }
      processCharacters(chars.tail, index + 1)
    }

    processCharacters(text.toList)
    if (line.nonEmpty) {
      lines += line.toVector
    }
    Text(font, text, size, maxWidth, kerning, lines.toVector)
  }
}

object WrapMode {
  case object None extends WrapMode
  case object Clip extends WrapMode
  case object Ellipsis extends WrapMode
  case object Hyphenate extends WrapMode
  case object Word extends WrapMode
}