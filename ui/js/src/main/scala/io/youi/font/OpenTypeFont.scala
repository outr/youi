package io.youi.font

import io.youi.net.URL

import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class OpenTypeFont(otf: opentype.Font) extends Font {
  private var glyphs = Map.empty[Char, OpenTypeGlyph]

  override def lineHeight(size: Double): Double = (otf.ascender - otf.descender) * (1.0 / otf.unitsPerEm * size)

  def ascender(size: Double): Double = otf.ascender * (1.0 / otf.unitsPerEm * size)

  override def apply(text: String,
                     size: Double,
                     maxWidth: Double = Double.MaxValue,
                     kerning: Boolean = true,
                     wrap: WrapMode = WrapMode.Word): Text = {
    var offsetX = 0.0
    var offsetY = 0.0
    val lineHeight = this.lineHeight(size)
    var previous: Option[OpenTypeGlyph] = None
    val line = ListBuffer.empty[CharacterPath]
    val lines = ListBuffer.empty[Vector[CharacterPath]]
    val unitsPerEm = otf.unitsPerEm

    def processCharacters(chars: List[Char], index: Int = 0): Unit = if (chars.nonEmpty) {
      val char = chars.head
      if (char == '\n') {
        offsetX = 0.0
        offsetY += lineHeight
        previous = None
      } else {
        val glyph = glyphs.get(char) match {
          case Some(g) => g
          case None => {
            val g = OpenTypeGlyph(this, otf.charToGlyph(char.toString), unitsPerEm)
            glyphs += char -> g
            g
          }
        }
        var kernOffset = if (kerning && previous.nonEmpty) {
          otf.getKerningValue(previous.get.otg, glyph.otg) * (1.0 / otf.unitsPerEm * size)
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
    Text(this, text, size, maxWidth, kerning, lines.toVector)
  }
}

object OpenTypeFont {
  private var pathMap = Map.empty[String, OpenTypeFont]

  def fromPath(path: String): Future[OpenTypeFont] = {
    pathMap.get(path) match {
      case Some(font) => Future.successful(font)
      case None => {
        opentype.OpenType.load(path).map { otf =>
          val font = new OpenTypeFont(otf)
          pathMap += path -> font
          font
        }
      }
    }
  }

  def fromURL(url: URL): Future[OpenTypeFont] = fromPath(url.toString)
}