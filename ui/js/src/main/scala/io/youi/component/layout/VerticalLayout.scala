package io.youi.component.layout

import io.youi.component.{AbstractContainer, Component}
import reactify._

class VerticalLayout(spacing: Double = 0.0,
                     fromTop: Boolean = true) extends Layout {
  override def connect(container: AbstractContainer): Unit = {
    update(container, Vector.empty)
  }

  override def disconnect(container: AbstractContainer): Unit = AbstractContainer.children(container).foreach { c =>
    Snap(c).verticalReset()
  }

  override def childrenChanged(container: AbstractContainer, removed: Vector[Component], added: Vector[Component]): Unit = {
    super.childrenChanged(container, removed, added)

    update(container, removed)
  }

  private def update(container: AbstractContainer, removed: Vector[Component]): Unit = {
    val children = AbstractContainer.children(container)
    val items = if (fromTop) children() else children().reverse
    removed.foreach { c =>
      Snap(c).verticalReset()
    }
    if (items.nonEmpty) {
      var previous = items.head
      if (fromTop) {
        Snap(previous).verticalReset().topTo(container.position.top + spacing)
      } else {
        Snap(previous).verticalReset().bottomTo(container.position.bottom - spacing)
      }
      items.tail.foreach { child =>
        if (fromTop) {
          Snap(child).verticalReset().topTo(previous.position.bottom + spacing)
        } else {
          Snap(child).verticalReset().bottomTo(previous.position.top - spacing)
        }
        previous = child
      }
    }
  }
}