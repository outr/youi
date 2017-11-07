package io.youi.layout

import io.youi.{Widget, WidgetContainer}
import reactify._

class FlowLayout(margins: Margins = Margins()) extends Layout {
  private var map = Map.empty[WidgetContainer, Val[Double]]

  override def connect(container: WidgetContainer): Unit = {
    val v = Val(WidgetContainer.children(container)().map(w => w.size.width + w.size.height).sum)
    map += container -> v
    v.on(update(container))
    update(container)
  }

  override def disconnect(container: WidgetContainer): Unit = {
    update(container)
    map(container).clearObservers()
    map -= container
  }

  override def resized(container: WidgetContainer, width: Double, height: Double): Unit = update(container)

  override def childrenChanged(container: WidgetContainer, removed: Vector[Widget], added: Vector[Widget]): Unit = update(container)

  private def update(container: WidgetContainer): Unit = {
    var x: Double = 0.0
    var y: Double = margins.top
    var rowCount = 0
    var rowMaxHeight = 0.0
    WidgetContainer.children(container).foreach { widget =>
      x += margins.left
      if (rowCount > 0 && x + widget.size.width > container.size.width) {
        x = margins.left
        y += rowMaxHeight + margins.bottom
        rowCount = 0
        rowMaxHeight = 0.0
      }
      widget.position.left := x
      widget.position.top := y
      x += widget.size.width
      rowCount += 1
      rowMaxHeight = math.max(rowMaxHeight, widget.size.height)
    }
  }
}