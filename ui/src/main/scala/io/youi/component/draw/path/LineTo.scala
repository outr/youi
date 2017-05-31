package io.youi.component.draw.path

import org.scalajs.dom.raw.CanvasRenderingContext2D

case class LineTo(x: Double, y: Double) extends PathAction {
  override def invoke(context: CanvasRenderingContext2D): Unit = context.lineTo(x, y)
}
