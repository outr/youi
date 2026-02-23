package io.youi.layout

import io.youi.component.Component
import io.youi.component.feature.ContainerFeature
import io.youi.component.types.Display

trait Layout {
  def connect(container: Component, children: ContainerFeature[Component]): Unit
  def disconnect(container: Component, children: ContainerFeature[Component]): Unit
  def resized(container: Component, children: ContainerFeature[Component]): Unit
  def childrenChanged(container: Component, children: Vector[Component]): Unit

  protected def visible(c: Component): Boolean = c.display() != Display.None
}
