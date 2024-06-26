package io.youi.font

import cats.effect.IO
import spice.net.URL

case class OpenTypeFont(otf: opentype.Font) extends Font {
  private var glyphs = Map.empty[Char, OpenTypeGlyph]

  lazy val cached: CachedFont = new CachedFont(this)

  override def glyph(char: Char): Glyph = glyphs.get(char) match {
    case Some(g) => g
    case None =>
      val g = OpenTypeGlyph(this, char, otf.charToGlyph(char.toString), otf.unitsPerEm)
      glyphs += char -> g
      g
  }

  override def kerning(first: Glyph, second: Glyph, size: Double): Double = {
    otf.getKerningValue(first.asInstanceOf[OpenTypeGlyph].otg, second.asInstanceOf[OpenTypeGlyph].otg) * (1.0 / otf.unitsPerEm * size)
  }

  override def lineHeight(size: Double): Double = ascender(size) - descender(size)

  override def ascender(size: Double): Double = otf.ascender * (1.0 / otf.unitsPerEm * size)

  override def descender(size: Double): Double = otf.descender * (1.0 / otf.unitsPerEm * size)

  override protected def createBuilder(text: String, size: Double, maxWidth: Double, kerning: Boolean): TextBuilder = {
    new OpenTypeTextBuilder(this, text, size, maxWidth, kerning)
  }
}

object OpenTypeFont {
  private var pathMap = Map.empty[String, OpenTypeFont]

  def fromPath(path: String, cached: Boolean = true): IO[Font] = {
    val openTypeFuture = pathMap.get(path) match {
      case Some(font) => IO.pure(font)
      case None =>
        opentype.OpenType.load(path).flatMap {
          case Left(t) => IO.raiseError(t)
          case Right(otf) => IO {
            val font = new OpenTypeFont(otf)
            pathMap += path -> font
            font
          }
        }
    }
    if (cached) {
      openTypeFuture.map(_.cached)
    } else {
      openTypeFuture
    }
  }

  def fromURL(url: URL, cached: Boolean = true): IO[Font] = fromPath(url.toString)
}