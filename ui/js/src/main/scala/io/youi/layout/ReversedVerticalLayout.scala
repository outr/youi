package io.youi.layout

class ReversedVerticalLayout(spacing: Double = 0.0) extends Layout {
  override def connect(container: Component): Unit = {
    update(container, Vector.empty)
  }

  override def disconnect(container: Component): Unit = Component.childrenFor(container).foreach { c =>
    Snap(c).verticalReset()
  }

  override def childrenChanged(container: Component, removed: Vector[Component], added: Vector[Component]): Unit = {
    super.childrenChanged(container, removed, added)

    update(container, removed)
  }

  private def update(container: Component, removed: Vector[Component]): Unit = {
    val items = Component.childrenFor(container)
    removed.foreach { c =>
      Snap(c).verticalReset()
    }
    items.filter(c => c.visible() && c.includeInLayout()).foldLeft(Option.empty[Component])((previous, current) => {
      Snap(current).verticalReset().bottomTo(previous.map(_.position.top()).getOrElse(container.size.height.value) - spacing)
      Some(current)
    })
  }
}