package youi.layout

import youi.component.Component
import youi.component.feature.ContainerFeature

case class HorizontalLayout(spacing: Double = 0.0) extends Layout {
  override def connect(container: Component, children: ContainerFeature[Component]): Unit =
    layout(children.get)

  override def disconnect(container: Component, children: ContainerFeature[Component]): Unit = ()

  override def resized(container: Component, children: ContainerFeature[Component]): Unit =
    layout(children.get)

  override def childrenChanged(container: Component, children: Vector[Component]): Unit =
    layout(children)

  private def layout(children: Vector[Component]): Unit = {
    var x = 0.0
    children.foreach { child =>
      if (visible(child)) {
        child.position.left @= x
        x += child.effectiveWidth() + spacing
      }
    }
  }
}
