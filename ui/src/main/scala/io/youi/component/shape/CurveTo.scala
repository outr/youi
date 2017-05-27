package io.youi.component.shape

import org.scalajs.dom.raw.CanvasRenderingContext2D

case class CurveTo(x1: Double, y1: Double, x2: Double, y2: Double, x: Double, y: Double) extends PathAction {
  override def invoke(context: CanvasRenderingContext2D): Unit = context.bezierCurveTo(x1, y1, x2, y2, x, y)
}
