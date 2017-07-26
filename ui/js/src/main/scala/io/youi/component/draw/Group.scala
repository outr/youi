package io.youi.component.draw
import io.youi.component.Component
import org.scalajs.dom.raw.CanvasRenderingContext2D

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class Group(drawables: List[Drawable]) extends Drawable {
  override lazy val boundingBox: BoundingBox = {
    val bounds = drawables.collect {
      case d if d.boundingBox != BoundingBox.zero => d.boundingBox
    }
    if (bounds.nonEmpty) {
      var b = bounds.head
      bounds.tail.foreach { bound =>
        b = b.merge(bound)
      }
      b
    } else {
      BoundingBox.zero
    }
  }

  def withDrawables(drawables: Drawable*): Group = Group(this.drawables ::: drawables.toList)

  override def draw(component: Component, context: CanvasRenderingContext2D): Future[Unit] = {
    Future.sequence(drawables.map { d =>
      d.draw(component, context)
    }).map(_ => ())
  }
}

object Group {
  def apply(drawables: Drawable*): Group = Group(drawables.toList)
}