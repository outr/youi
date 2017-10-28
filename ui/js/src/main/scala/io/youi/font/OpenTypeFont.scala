package io.youi.font

import io.youi.net.URL

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class OpenTypeFont(otf: opentype.Font) extends Font {
  private var glyphs = Map.empty[Char, OpenTypeGlyph]

  lazy val cached: CachedFont = new CachedFont(this)

  override def glyph(char: Char): Glyph = glyphs.get(char) match {
    case Some(g) => g
    case None => {
      val g = OpenTypeGlyph(this, char, otf.charToGlyph(char.toString), otf.unitsPerEm)
      glyphs += char -> g
      g
    }
  }

  override def kerning(first: Glyph, second: Glyph, size: Double): Double = {
    otf.getKerningValue(first.asInstanceOf[OpenTypeGlyph].otg, second.asInstanceOf[OpenTypeGlyph].otg) * (1.0 / otf.unitsPerEm * size)
  }

  override def lineHeight(size: Double): Double = (otf.ascender - otf.descender) * (1.0 / otf.unitsPerEm * size)

  def ascender(size: Double): Double = otf.ascender * (1.0 / otf.unitsPerEm * size)

  override protected def createBuilder(text: String, size: Double, maxWidth: Double, kerning: Boolean): TextBuilder = {
    new OpenTypeTextBuilder(this, text, size, maxWidth, kerning)
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