package io.youi.path

import io.youi.drawable.Context

case class Rectangle(x: Double,
                     y: Double,
                     width: Double,
                     height: Double,
                     begin: Boolean = true,
                     close: Boolean = true) extends PathAction {
  def left: Double = x
  def top: Double = y
  def right: Double = x + width
  def bottom: Double = y + height
  def center: Double = x + (width / 2.0)
  def middle: Double = y + (height / 2.0)

  override def draw(context: Context, x: Double, y: Double, scaleX: Double, scaleY: Double): Unit = {
    context.rect(x + (this.x * scaleX), y + (this.y * scaleY), width * scaleX, height * scaleY, begin, close)
  }

  override def toString: String = s"Rectangle(x: $x, y: $y, width: $width, height: $height)"
}