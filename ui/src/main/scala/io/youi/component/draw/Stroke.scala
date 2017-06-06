package io.youi.component.draw

import io.youi.component.Component
import io.youi.style.Paint
import org.scalajs.dom.raw.CanvasRenderingContext2D

import scala.scalajs.js

case class Stroke(paint: Paint, lineWidth: Double, lineDash: List[Double]) extends Drawable {
  def set(component: Component, context: CanvasRenderingContext2D): Unit = {
    if (paint.nonEmpty) {
      context.strokeStyle = paint(component).asInstanceOf[js.Any]
      context.lineWidth = lineWidth
      context.setLineDash(js.Array(lineDash: _*))
    }
  }
  def stroke(context: CanvasRenderingContext2D): Unit = {
    if (paint.nonEmpty) {
      context.stroke()
    }
  }

  override def boundingBox: BoundingBox = BoundingBox.zero

  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = {
    set(component, context)
    stroke(context)
  }
}
