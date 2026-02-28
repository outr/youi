package youi.layout

import youi.component.Component
import youi.component.feature.ContainerFeature
import youi.component.types.Display

trait Layout {
  def connect(container: Component, children: ContainerFeature[Component]): Unit
  def disconnect(container: Component, children: ContainerFeature[Component]): Unit
  def resized(container: Component, children: ContainerFeature[Component]): Unit
  def childrenChanged(container: Component, children: Vector[Component]): Unit

  protected def visible(c: Component): Boolean = c.display() != Display.None
}
