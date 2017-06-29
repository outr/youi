package io.youi.component.draw

import io.youi.Priority
import io.youi.component.Component
import org.scalajs.dom.raw.CanvasRenderingContext2D

import scala.concurrent.Future

trait Drawable extends Ordered[Drawable] {
  def priority: Priority = Priority.Normal
  def draw(component: Component, context: CanvasRenderingContext2D): Future[Unit]
  def boundingBox: BoundingBox

  override def compare(that: Drawable): Int = priority.compare(that.priority)
}

object Drawable {
  case object empty extends Drawable {
    override def draw(component: Component, context: CanvasRenderingContext2D): Future[Unit] = {
      Future.successful(())
    }
    override def boundingBox: BoundingBox = BoundingBox.zero
  }
}