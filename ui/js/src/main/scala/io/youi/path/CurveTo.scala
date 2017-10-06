package io.youi.path

import io.youi.Context

case class CurveTo(x1: Double, y1: Double, x2: Double, y2: Double, x: Double, y: Double) extends PathAction {
  override def draw(context: Context, x: Double, y: Double): Unit = context.bezierCurveTo(x1, y1, x2, y2, x, y)
}
