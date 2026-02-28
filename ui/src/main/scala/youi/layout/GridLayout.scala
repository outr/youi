package youi.layout

import youi.component.Component
import youi.component.feature.ContainerFeature

case class GridLayout(columns: Int,
                      horizontalSpacing: Double = 0.0,
                      verticalSpacing: Double = 0.0) extends Layout {
  override def connect(container: Component, children: ContainerFeature[Component]): Unit =
    layout(container, children.get)

  override def disconnect(container: Component, children: ContainerFeature[Component]): Unit = ()

  override def resized(container: Component, children: ContainerFeature[Component]): Unit =
    layout(container, children.get)

  override def childrenChanged(container: Component, children: Vector[Component]): Unit =
    layout(container, children)

  private def layout(container: Component, children: Vector[Component]): Unit = {
    val containerWidth = container.effectiveWidth()
    if (containerWidth <= 0.0 || columns <= 0) return

    val totalSpacing = horizontalSpacing * (columns - 1)
    val cellWidth = (containerWidth - totalSpacing) / columns

    val visibleChildren = children.filter(visible)
    val rows = visibleChildren.grouped(columns).toVector

    var y = 0.0
    rows.foreach { row =>
      var rowHeight = 0.0
      row.zipWithIndex.foreach { case (child, col) =>
        val x = col * (cellWidth + horizontalSpacing)
        child.position.left @= x
        child.position.top @= y
        child.size.width @= cellWidth
        rowHeight = math.max(rowHeight, child.effectiveHeight())
      }
      y += rowHeight + verticalSpacing
    }
  }
}
