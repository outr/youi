package io.youi.component.draw
import io.youi.component.Component
import org.scalajs.dom.raw.CanvasRenderingContext2D

case class Translate(x: Double = 0.0, y: Double = 0.0) extends Drawable {
  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = {
    context.translate(x, y)
  }
}