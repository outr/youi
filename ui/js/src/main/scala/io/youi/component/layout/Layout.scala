package io.youi.component.layout

import io.youi.component.{AbstractContainer, Component}
import reactify.{InvocationType, Listener}

/**
  * Layouts can be defined and connected to an AbstractContainer to manage the layout of the children. By default
  * Layout.None is defined and each child is responsible for its own positioning and sizing.
  */
trait Layout {
  def connect(container: AbstractContainer): Unit

  def disconnect(container: AbstractContainer): Unit

  def resized(container: AbstractContainer, width: Double, height: Double): Unit = {}

  def childrenChanged(container: AbstractContainer, removed: Vector[Component], added: Vector[Component]): Unit = {}
}

object Layout {
  object None extends Layout {
    override def connect(container: AbstractContainer): Unit = {}

    override def disconnect(container: AbstractContainer): Unit = {}
  }
}