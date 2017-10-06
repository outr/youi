package io.youi.path

import io.youi.Context

case class LineTo(x: Double, y: Double) extends PathAction {
  override def draw(context: Context, x: Double, y: Double): Unit = context.lineTo(x, y)
}
