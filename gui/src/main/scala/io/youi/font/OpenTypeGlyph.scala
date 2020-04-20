package io.youi.font

import io.youi.drawable.Context
import io.youi.path.Path

case class OpenTypeGlyph(font: OpenTypeFont, char: Char, otg: opentype.Glyph, unitsPerEm: Double) extends Glyph {
  override lazy val path: Path = try {
    Path(otg.path.toPathData()).withoutOpen().withoutClose()
  } catch {
    case _: Throwable => Path.empty
  }
  override def width(size: Double): Double = otg.advanceWidth * (1.0 / unitsPerEm * size)

  override def actualWidth(size: Double): Double = sizedPath(size).boundingBox.x2

  override def sizedPath(size: Double): Path = {
    val scale = 1.0 / unitsPerEm * size
    path.scale(scale, -scale).shift(0.0, font.ascender(size))
  }

  override def draw(context: Context, x: Double, y: Double, size: Double): Unit = {
    val scale = 1.0 / unitsPerEm * size
    path.draw(context, x, y + font.ascender(size), scale, -scale)
  }
}
