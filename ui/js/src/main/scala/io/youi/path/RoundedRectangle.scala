package io.youi.path

import io.youi.Context

case class RoundedRectangle(x: Double,
                            y: Double,
                            width: Double,
                            height: Double,
                            radius: Double) extends PathAction {
  override def draw(context: Context, x: Double, y: Double): Unit = context.roundedRect(x + this.x, y + this.y, width, height, radius)

  override def toString: String = s"RoundedRectangle(x: $x, y: $y, width: $width, height: $height, radius: $radius)"
}