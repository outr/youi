package io.youi.component.draw
import io.youi.component.Component
import org.scalajs.dom.raw.CanvasRenderingContext2D

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

  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = drawables.foreach { d =>
    d.draw(component, context)
  }
}

object Group {
  def apply(drawables: Drawable*): Group = Group(drawables.toList)
}