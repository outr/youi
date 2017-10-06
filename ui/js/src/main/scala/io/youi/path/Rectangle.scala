package io.youi.path

import io.youi.Context

case class Rectangle(x: Double, y: Double, width: Double, height: Double) extends PathAction {
  override def draw(context: Context, x: Double, y: Double, scaleX: Double, scaleY: Double): Unit = {
    context.rect(x + (this.x * scaleX), y + (this.y * scaleY), width * scaleX, height * scaleY)
  }

  override def toString: String = s"Rectangle(x: $x, y: $y, width: $width, height: $height)"
}