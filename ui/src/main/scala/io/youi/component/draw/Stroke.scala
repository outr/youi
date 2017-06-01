package io.youi.component.draw

import io.youi.component.Component
import io.youi.style.Paint
import org.scalajs.dom.raw.CanvasRenderingContext2D

import scala.scalajs.js

case class Stroke(paint: Option[Paint] = None, lineDash: Option[List[Double]] = None) extends Drawable {
  override def boundingBox: BoundingBox = BoundingBox.zero

  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = {
    paint.foreach { p =>
      val value = p(component)
      context.strokeStyle = value.asInstanceOf[js.Any]
    }
    lineDash.foreach { list =>
      context.setLineDash(js.Array(list: _*))
    }
    context.stroke()
  }
}