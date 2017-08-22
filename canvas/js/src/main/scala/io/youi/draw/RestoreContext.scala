package io.youi.draw

import io.youi.{BoundingBox, Context}
import io.youi.component.Component

object RestoreContext extends Drawable {
  override def draw(component: Component, context: Context): Unit = context.restore()

  override def boundingBox: BoundingBox = BoundingBox.zero
}
