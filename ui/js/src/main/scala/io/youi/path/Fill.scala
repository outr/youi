package io.youi.path

import io.youi.Context
import io.youi.paint.Paint

case class Fill(paint: Paint, apply: Boolean = true) extends PathAction {
  override def draw(context: Context, x: Double, y: Double, scaleX: Double, scaleY: Double): Unit = context.fill(paint, apply)
}