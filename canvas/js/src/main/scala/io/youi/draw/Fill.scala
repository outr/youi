package io.youi.draw

import io.youi.Context
import io.youi.component.Component
import io.youi.paint.Paint
import io.youi.spatial.BoundingBox

case class Fill(paint: Paint) extends Drawable {
  override def boundingBox: BoundingBox = BoundingBox.zero

  def set(component: Component, context: Context): Unit = {
    context.fill(paint, apply = false)
  }

  override def draw(component: Component, context: Context): Unit = {
    context.fill(paint, apply = true)
  }
}