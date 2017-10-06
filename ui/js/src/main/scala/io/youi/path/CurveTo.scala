package io.youi.path

import io.youi.Context

case class CurveTo(x1: Double, y1: Double, x2: Double, y2: Double, x: Double, y: Double) extends PathAction {
  override def draw(context: Context, x: Double, y: Double): Unit = {
    context.bezierCurveTo(x + x1, y + y1, x + x2, y + y2, x + this.x, y + this.y)
  }

  override def toString: String = s"CurveTo(x1: $x1, y1: $y1, x2: $x2, y2: $y2, x: $x, y: $y)"
}
