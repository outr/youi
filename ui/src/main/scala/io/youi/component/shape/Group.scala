package io.youi.component.shape
import io.youi.component.Component
import org.scalajs.dom.raw.CanvasRenderingContext2D

case class Group(drawables: List[Drawable]) extends Drawable {
  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = drawables.foreach { d =>
    d.draw(component, context)
  }
}

object Group {
  def apply(drawables: Drawable*): Group = Group(drawables.toList)
}