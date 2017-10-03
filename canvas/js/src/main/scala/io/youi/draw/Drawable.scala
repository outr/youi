package io.youi.draw

import io.youi.{Context, Priority}
import io.youi.component.Component
import io.youi.spatial.BoundingBox

trait Drawable extends Ordered[Drawable] {
  def priority: Priority = Priority.Normal
  def draw(component: Component, context: Context): Unit
  def boundingBox: BoundingBox

  override def compare(that: Drawable): Int = priority.compare(that.priority)
}

object Drawable {
  case object empty extends Drawable {
    override def draw(component: Component, context: Context): Unit = {}
    override def boundingBox: BoundingBox = BoundingBox.zero
  }
}