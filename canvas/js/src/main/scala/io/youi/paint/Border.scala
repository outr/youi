package io.youi.paint

import io.youi.{Compass, Context}

trait Border {
  def size(compass: Compass): Double
  def draw(width: Double, height: Double, context: Context): Unit
}

object Border {
  def empty: Border = EmptyBorder

  def apply(stroke: Stroke, radius: Double = 0.0): RectangleBorder = RectangleBorder(stroke, radius)
}

object EmptyBorder extends Border {
  override def size(compass: Compass): Double = 0.0

  override def draw(width: Double, height: Double, context: Context): Unit = {}
}

case class RectangleBorder(stroke: Stroke, radius: Double) extends Border {
  override def size(compass: Compass): Double = stroke.lineWidth

  override def draw(width: Double, height: Double, context: Context): Unit = {
    if (radius == 0.0) {
      context.rect(0.0, 0.0, width, height)
    } else {
      context.roundedRect(0.0, 0.0, width, height, radius)
    }
    context.stroke(stroke, apply = true)
  }
}