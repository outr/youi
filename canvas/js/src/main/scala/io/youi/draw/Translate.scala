package io.youi.draw

import io.youi.{BoundingBox, Context}
import io.youi.component.Component

case class Translate(x: Double = 0.0, y: Double = 0.0) extends Drawable {
  override def boundingBox: BoundingBox = BoundingBox.zero

  override def draw(component: Component, context: Context): Unit = context.translate(x, y)
}