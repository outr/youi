package io.youi.path

import io.youi.Context

case class RoundedRectangle(x: Double,
                            y: Double,
                            width: Double,
                            height: Double,
                            radius: Double) extends PathAction {
  override def draw(context: Context, x: Double, y: Double): Unit = context.roundedRect(x, y, width, height, radius)
}