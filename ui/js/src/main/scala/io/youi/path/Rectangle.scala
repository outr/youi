package io.youi.path

import io.youi.Context

case class Rectangle(x: Double, y: Double, width: Double, height: Double) extends PathAction {
  override def draw(context: Context, x: Double, y: Double): Unit = context.rect(x, y, width, height)
}