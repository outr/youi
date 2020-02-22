package io.youi.path

import io.youi.drawable.Context

case class RoundedRectangle(x: Double,
                            y: Double,
                            width: Double,
                            height: Double,
                            radius: Double) extends PathAction {
  override def draw(context: Context, x: Double, y: Double, scaleX: Double, scaleY: Double): Unit = {
    context.roundedRect(x + (this.x * scaleX), y + (this.y * scaleY), width * scaleX, height * scaleY, radius * scaleX)
  }

  override def toString: String = s"RoundedRectangle(x: $x, y: $y, width: $width, height: $height, radius: $radius)"
}