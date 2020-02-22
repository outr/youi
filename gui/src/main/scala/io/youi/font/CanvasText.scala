package io.youi.font

import io.youi.drawable.Context
import io.youi.paint.{Paint, Stroke}

case class CanvasText(font: CanvasFont, text: String, size: Double, maxWidth: Double, kerning: Boolean) extends Text {
  override def lines: Vector[Vector[CharacterPath]] = Vector.empty

  override def draw(context: Context, x: Double, y: Double, fill: Paint, stroke: Stroke): Unit = {
    font(context, size)
    scribe.info(s"Font: ${context.ctx.font}")
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