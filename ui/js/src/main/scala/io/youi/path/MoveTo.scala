package io.youi.path

import io.youi.Context

case class MoveTo(x: Double, y: Double) extends PathAction {
  override def draw(context: Context, x: Double, y: Double): Unit = context.moveTo(this.x + x, this.y + y)

  override def toString: String = s"MoveTo(x: $x, y: $y)"
}
