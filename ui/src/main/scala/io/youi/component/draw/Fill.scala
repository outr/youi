package io.youi.component.draw

import io.youi.component.Component
import io.youi.style.Paint
import org.scalajs.dom.raw.CanvasRenderingContext2D

import scala.scalajs.js

case class Fill(paint: Option[Paint]) extends Drawable {
  override def boundingBox: BoundingBox = BoundingBox.zero

  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = {
    paint.foreach { p =>
      val value = p(component)
      context.fillStyle = value.asInstanceOf[js.Any]
    }
    context.fill()
  }
}