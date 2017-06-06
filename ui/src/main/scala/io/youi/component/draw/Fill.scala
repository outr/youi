package io.youi.component.draw

import io.youi.component.Component
import io.youi.style.Paint
import org.scalajs.dom.raw.CanvasRenderingContext2D

import scala.scalajs.js

case class Fill(paint: Paint) extends Drawable {
  override def boundingBox: BoundingBox = BoundingBox.zero

  def set(component: Component, context: CanvasRenderingContext2D): Unit = {
    if (paint.nonEmpty) {
      val value = paint(component)
      context.fillStyle = value.asInstanceOf[js.Any]
    }
  }

  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = {
    set(component, context)
    fill(context)
  }

  def fill(context: CanvasRenderingContext2D): Unit = {
    if (paint.nonEmpty) {
      context.fill()
    }
  }
}