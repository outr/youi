package youi.font

import youi.drawable.Context
import youi.paint.{Paint, Stroke}
import youi.spatial.BoundingBox

case class CanvasText(font: CanvasFont, text: String, size: Double, maxWidth: Double, kerning: Boolean) extends Text {
  lazy val boundingBox: BoundingBox = {
    val measured = CanvasFont.measure(text, font, size)
    BoundingBox(x2 = measured.width, y2 = measured.height)
  }

  override def draw(context: Context, x: Double, y: Double, fill: Paint, stroke: Stroke): Unit = {
    font(context, size)
    if (fill.nonEmpty) {
      context.fill(fill, apply = false)
      context.fillText(text, x, y)
    }
    if (stroke.nonEmpty) {
      context.stroke(stroke, apply = false)
      context.strokeText(text, x, y)
    }
  }
}