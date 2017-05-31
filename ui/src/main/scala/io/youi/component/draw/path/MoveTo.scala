package io.youi.component.draw.path

import org.scalajs.dom.raw.CanvasRenderingContext2D

case class MoveTo(x: Double, y: Double) extends PathAction {
  override def invoke(context: CanvasRenderingContext2D): Unit = context.moveTo(x, y)
}
