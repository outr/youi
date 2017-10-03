package io.youi.draw

import io.youi.Context
import io.youi.component.Component
import io.youi.spatial.BoundingBox

case class Translate(x: Double = 0.0, y: Double = 0.0) extends Drawable {
  override def boundingBox: BoundingBox = BoundingBox.zero

  override def draw(component: Component, context: Context): Unit = context.translate(x, y)
}