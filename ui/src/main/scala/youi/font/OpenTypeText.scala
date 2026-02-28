package youi.font

import youi.drawable.Context
import youi.paint.{Paint, Stroke}
import youi.path.{Fill, Path}
import youi.spatial.BoundingBox

case class OpenTypeText(font: Font,
                        text: String,
                        size: Double,
                        maxWidth: Double,
                        kerning: Boolean,
                        lines: Vector[Vector[CharacterPath]]) extends Text {
  lazy val boundingBox: BoundingBox = {
    val bb = BoundingBox.temp.zero()
    lines.foreach { line =>
      line.foreach { character =>
        bb.set(x2 = math.max(bb.x2, character.x + character.glyph.actualWidth(size)))
      }
    }
    bb.set(y1 = 0.0, y2 = lineHeight * lines.length)
    bb.immutable
  }

  lazy val path: Path = Path.merge(lines.flatten.map(cp => cp.path)*)

  override def draw(context: Context, x: Double, y: Double, fill: Paint, stroke: Stroke): Unit = {
    Fill.draw(context, fill, Some(path))
    if (stroke.nonEmpty) {
      Stroke.draw(
        context = context,
        paint = stroke.paint,
        path = Some(path),
        lineWidth = stroke.lineWidth,
        lineDash = stroke.lineDash,
        lineDashOffset = stroke.lineDashOffset,
        lineCap = stroke.lineCap,
        lineJoin = stroke.lineJoin
      )
    }
  }
}