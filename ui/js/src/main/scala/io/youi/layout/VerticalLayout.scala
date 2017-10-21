package io.youi.layout

import io.youi.{Widget, WidgetContainer}
import reactify._

class VerticalLayout(spacing: Double = 0.0,
                     fromTop: Boolean = true) extends Layout {
  override def connect(container: WidgetContainer): Unit = {
    update(container, Vector.empty)
  }

  override def disconnect(container: WidgetContainer): Unit = WidgetContainer.children(container).foreach { c =>
    Snap(c).verticalReset()
  }

  override def childrenChanged(container: WidgetContainer, removed: Vector[Widget], added: Vector[Widget]): Unit = {
    super.childrenChanged(container, removed, added)

    update(container, removed)
  }

  private def update(container: WidgetContainer, removed: Vector[Widget]): Unit = {
    val children = WidgetContainer.children(container)
    val items = children()
    removed.foreach { c =>
      Snap(c).verticalReset()
    }
    if (items.nonEmpty) {
      var previous = items.head
      if (fromTop) {
        Snap(previous).verticalReset().topTo(spacing)
      } else {
        Snap(previous).verticalReset().bottomTo(container.size.height - spacing)
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