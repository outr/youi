package io.youi.component.draw

import io.youi.component.Component
import org.scalajs.dom.raw.CanvasRenderingContext2D

object RestoreContext extends Drawable {
  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = context.restore()

  override def boundingBox: BoundingBox = BoundingBox.zero
}
