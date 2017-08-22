package io.youi.draw

import io.youi.{BoundingBox, Context}
import io.youi.component.Component

object SaveContext extends Drawable {
  override def draw(component: Component, context: Context): Unit = context.save()

  override def boundingBox: BoundingBox = BoundingBox.zero
}
