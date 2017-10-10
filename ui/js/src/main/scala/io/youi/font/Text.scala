package io.youi.font

import io.youi.drawable.{Context, Drawable}
import io.youi.path.Path
import io.youi.spatial.BoundingBox

case class Text(font: Font,
                text: String,
                size: Double,
                maxWidth: Double,
                kerning: Boolean,
                lines: Vector[Vector[CharacterPath]]) extends Drawable {
  lazy val boundingBox: BoundingBox = {
    val bb = BoundingBox.temp.zero()
    lines.foreach { line =>
      line.foreach { character =>
        bb.set(x2 = math.max(bb.x2, character.x + character.glyph.width(size)))
      }
    }
    bb.set(y1 = 0.0, y2 = lineHeight * lines.length)
    bb.immutable
  }
  def lineHeight: Double = font.lineHeight(size)

  lazy val path: Path = Path.merge(lines.flatten.map(cp => cp.path): _*)

  override def draw(context: Context, x: Double, y: Double): Unit = {
    context.begin()
    lines.foreach { line =>
      line.foreach(_.draw(context, x, y))
    }
    context.close()
  }
}

object Text {
  lazy val empty: Text = Text(
    font = Font.empty,
    text = "",
    size = 0.0,
    maxWidth = 0.0,
    kerning = false,
    lines = Vector.empty
  )
}