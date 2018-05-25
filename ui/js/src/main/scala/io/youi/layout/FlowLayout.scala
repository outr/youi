package io.youi.layout

import io.youi.component.Component
import reactify._

class FlowLayout(margins: Margins = Margins()) extends Layout {
  private var map = Map.empty[Component, Val[Double]]

  override def connect(container: Component): Unit = {
    val v = Val(Component.childrenFor(container).map(w => w.size.actual.width + w.size.actual.height).sum)
    map += container -> v
    v.on(update(container))
    update(container)
  }

  override def disconnect(container: Component): Unit = {
    update(container)
    map(container).clearObservers()
    map -= container
  }

  override def resized(container: Component, width: Double, height: Double): Unit = update(container)

  override def childrenChanged(container: Component, removed: Vector[Component], added: Vector[Component]): Unit = update(container)

  private def update(container: Component): Unit = {
    var x: Double = 0.0
    var y: Double = margins.top
    var rowCount = 0
    var rowMaxHeight = 0.0
    Component.childrenFor(container).foreach { widget =>
      x += margins.left
      if (rowCount > 0 && x + widget.size.actual.width > container.size.actual.width) {
        x = margins.left
        y += rowMaxHeight + margins.bottom
        rowCount = 0
        rowMaxHeight = 0.0
      }
      widget.position.left := x
      widget.position.top := y
      x += widget.size.actual.width
      rowCount += 1
      rowMaxHeight = math.max(rowMaxHeight, widget.size.actual.height)
    }
  }
}