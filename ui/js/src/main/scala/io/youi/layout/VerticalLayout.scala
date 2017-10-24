package io.youi.layout

import io.youi.{Widget, WidgetContainer}
import reactify._

class VerticalLayout(spacing: Double = 0.0) extends Layout {
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
    items.foldLeft(Option.empty[Widget])((previous, current) => {
      Snap(current).verticalReset().topTo(previous.map(_.position.bottom()).getOrElse(0.0) + spacing)
      Some(current)
    })
  }
}