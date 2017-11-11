package io.youi.layout

import io.youi.component.Component

/**
  * Layouts can be defined and connected to an AbstractContainer to manage the layout of the children. By default
  * Layout.None is defined and each child is responsible for its own positioning and sizing.
  */
trait Layout {
  def connect(container: Component): Unit

  def disconnect(container: Component): Unit

  def resized(container: Component, width: Double, height: Double): Unit = {}

  def childrenChanged(container: Component, removed: Vector[Component], added: Vector[Component]): Unit = {}
}

object Layout {
  object None extends Layout {
    override def connect(container: Component): Unit = {}

    override def disconnect(container: Component): Unit = {}
  }
}