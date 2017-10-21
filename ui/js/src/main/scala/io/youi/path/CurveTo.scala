package io.youi.path

import io.youi.drawable.Context

case class CurveTo(x1: Double, y1: Double, x2: Double, y2: Double, x: Double, y: Double) extends PathAction {
  override def draw(context: Context, x: Double, y: Double, scaleX: Double, scaleY: Double): Unit = {
    context.bezierCurveTo(x + (x1 * scaleX), y + (y1 * scaleY), x + (x2 * scaleX), y + (y2 * scaleY), x + (this.x * scaleX), y + (this.y * scaleY))
  }

  override def toString: String = s"CurveTo(x1: $x1, y1: $y1, x2: $x2, y2: $y2, x: $x, y: $y)"
}
