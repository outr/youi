package io.youi.layout

import io.youi.component.Component
import io.youi.component.feature.ContainerFeature

case class FlowLayout(horizontalSpacing: Double = 0.0, verticalSpacing: Double = 0.0) extends Layout {
  override def connect(container: Component, children: ContainerFeature[Component]): Unit =
    layout(container, children.get)

  override def disconnect(container: Component, children: ContainerFeature[Component]): Unit = ()

  override def resized(container: Component, children: ContainerFeature[Component]): Unit =
    layout(container, children.get)

  override def childrenChanged(container: Component, children: Vector[Component]): Unit =
    layout(container, children)

  private def layout(container: Component, children: Vector[Component]): Unit = {
    val containerWidth = container.effectiveWidth()
    if (containerWidth <= 0.0) return

    var x = 0.0
    var y = 0.0
    var rowHeight = 0.0

    children.foreach { child =>
      if (visible(child)) {
        val childWidth = child.effectiveWidth()
        val childHeight = child.effectiveHeight()

        if (x > 0.0 && x + childWidth > containerWidth) {
          x = 0.0
          y += rowHeight + verticalSpacing
          rowHeight = 0.0
        }

        child.position.left @= x
        child.position.top @= y
        x += childWidth + horizontalSpacing
        rowHeight = math.max(rowHeight, childHeight)
      }
    }
  }
}
