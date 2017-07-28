package io.youi.component.draw.path

import org.scalajs.dom.raw.CanvasRenderingContext2D

case class RoundedRectangle(x: Double,
                            y: Double,
                            width: Double,
                            height: Double,
                            radius: Double) extends PathAction {
  override def invoke(context: CanvasRenderingContext2D): Unit = {
    context.moveTo(x + radius, y)
    context.lineTo(x + width - radius, y)
    context.quadraticCurveTo(x + width, y, x + width, y + radius)
    context.lineTo(x + width, y + height - radius)
    context.quadraticCurveTo(x + width, y + height, x + width - radius, y + height)
    context.lineTo(x + radius, y + height)
    context.quadraticCurveTo(x, y + height, x, y + height - radius)
    context.lineTo(x, y + radius)
    context.quadraticCurveTo(x, y, x + radius, y)
  }
}