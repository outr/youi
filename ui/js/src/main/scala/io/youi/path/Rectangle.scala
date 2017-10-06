package io.youi.path

import io.youi.Context

case class Rectangle(x: Double, y: Double, width: Double, height: Double) extends PathAction {
  override def draw(context: Context, x: Double, y: Double): Unit = context.rect(x + this.x, y + this.y, width, height)

  override def toString: String = s"Rectangle(x: $x, y: $y, width: $width, height: $height)"
}