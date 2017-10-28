package io.youi.font

import io.youi.drawable.Context
import io.youi.paint.{Paint, Stroke}
import io.youi.path.{Fill, Path}

case class OpenTypeText(font: Font,
                        text: String,
                        size: Double,
                        maxWidth: Double,
                        kerning: Boolean,
                        lines: Vector[Vector[CharacterPath]]) extends Text {
  lazy val path: Path = Path.merge(lines.flatten.map(cp => cp.path): _*)

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