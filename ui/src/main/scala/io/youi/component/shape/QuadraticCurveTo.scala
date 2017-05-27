package io.youi.component.shape

import org.scalajs.dom.raw.CanvasRenderingContext2D

case class QuadraticCurveTo(x1: Double, y1: Double, x: Double, y: Double) extends PathAction {
  override def invoke(context: CanvasRenderingContext2D): Unit = context.quadraticCurveTo(x1, y1, x, y)
}
