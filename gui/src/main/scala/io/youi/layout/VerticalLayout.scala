package io.youi.layout

import io.youi.component.Component
import io.youi.component.feature.ContainerFeature

case class VerticalLayout(spacing: Double = 0.0) extends Layout {
  override def connect(container: Component, children: ContainerFeature[Component]): Unit =
    layout(children.get)

  override def disconnect(container: Component, children: ContainerFeature[Component]): Unit = ()

  override def resized(container: Component, children: ContainerFeature[Component]): Unit =
    layout(children.get)

  override def childrenChanged(container: Component, children: Vector[Component]): Unit =
    layout(children)

  private def layout(children: Vector[Component]): Unit = {
    var y = 0.0
    children.foreach { child =>
      if (visible(child)) {
        child.position.top @= y
        y += child.effectiveHeight() + spacing
      }
    }
  }
}
