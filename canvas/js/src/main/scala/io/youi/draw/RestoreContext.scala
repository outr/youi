package io.youi.draw

import io.youi.Context
import io.youi.component.Component
import io.youi.spatial.BoundingBox

object RestoreContext extends Drawable {
  override def draw(component: Component, context: Context): Unit = context.restore()

  override def boundingBox: BoundingBox = BoundingBox.zero
}
