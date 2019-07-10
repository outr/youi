package io.youi.layout

import io.youi.component.Component
import io.youi.layout.snap.Snap

class HorizontalLayout(spacing: Double = 0.0,
                       initialSpacing: Double = 0.0) extends Layout {
  override def connect(container: Component): Unit = {
    update(container, Vector.empty)
  }

  override def disconnect(container: Component): Unit = Component.childrenFor(container).foreach { c =>
    Snap(c).horizontalReset()
  }

  override def childrenChanged(container: Component, removed: Vector[Component], added: Vector[Component]): Unit = {
    super.childrenChanged(container, removed, added)

    update(container, removed)
  }

  private def update(container: Component, removed: Vector[Component]): Unit = {
    val items = Component.childrenFor(container)
    removed.foreach { c =>
      Snap(c).horizontalReset()
    }
    items.filter(c => c.visible() && c.includeInLayout()).foldLeft(Option.empty[Component])((previous, current) => {
      Snap(current).horizontalReset().leftTo(previous.map(_.position.right()).getOrElse(initialSpacing) + spacing)
      Some(current)
    })
  }
}