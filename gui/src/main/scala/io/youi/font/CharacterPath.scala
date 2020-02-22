package io.youi.font

import io.youi.drawable.{Context, Drawable}
import io.youi.path.Path

case class CharacterPath(char: Char, size: Double, index: Int, x: Double, y: Double, glyph: Glyph) extends Drawable {
  lazy val path: Path = glyph.sizedPath(size).shift(x, y)

  override def draw(context: Context, x: Double, y: Double): Unit = glyph.draw(context, this.x + x, this.y + y, size)
}