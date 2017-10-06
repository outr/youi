package io.youi.path

import io.youi.Context

case class QuadraticCurveTo(x1: Double, y1: Double, x: Double, y: Double) extends PathAction {
  override def draw(context: Context, x: Double, y: Double): Unit = {
    context.quadraticCurveTo(x + x1, y + y1, x + this.x, y + this.y)
  }

  override def toString: String = s"QuadraticCurveTo(x1: $x1, y1: $y1, x: $x, y: $y)"
}