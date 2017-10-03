package io.youi.draw

import io.youi.Context
import io.youi.component.Component
import io.youi.spatial.BoundingBox

object SaveContext extends Drawable {
  override def draw(component: Component, context: Context): Unit = context.save()

  override def boundingBox: BoundingBox = BoundingBox.zero
}
