package io.youi.component.shape

import io.youi.component.Component
import org.scalajs.dom.raw.CanvasRenderingContext2D

trait Drawable {
  def draw(component: Component, context: CanvasRenderingContext2D): Unit
}

object Drawable {
  case object empty extends Drawable {
    override def draw(component: Component, context: CanvasRenderingContext2D): Unit = {}
  }
}