package io.youi.path

import org.scalajs.dom.raw.CanvasRenderingContext2D

case class Rectangle(x: Double, y: Double, width: Double, height: Double) extends PathAction {
  override def invoke(context: CanvasRenderingContext2D): Unit = context.rect(x, y, width, height)
}