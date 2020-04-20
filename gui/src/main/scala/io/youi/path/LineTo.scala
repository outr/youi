package io.youi.path

import io.youi.drawable.Context

case class LineTo(x: Double, y: Double) extends PathAction {
  override def draw(context: Context, x: Double, y: Double, scaleX: Double, scaleY: Double): Unit = {
    context.lineTo(x + (this.x * scaleX), y + (this.y * scaleY))
  }

  override def toString: String = s"LineTo(x: $x, y: $y)"
}
