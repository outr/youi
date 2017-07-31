package io.youi.component.layout

import io.youi.component.AbstractContainer

/**
  * Layouts can be defined and connected to an AbstractContainer to manage the layout of the children. By default
  * Layout.None is defined and each child is responsible for its own positioning and sizing.
  */
trait Layout {
  protected def connect(container: AbstractContainer): Unit
  protected def disconnect(container: AbstractContainer): Unit
}

object Layout {
  object None extends Layout {
    override protected def connect(container: AbstractContainer): Unit = {}

    override protected def disconnect(container: AbstractContainer): Unit = {}
  }

  def connect(container: AbstractContainer, layout: Layout): Unit = layout.connect(container)

  def disconnect(container: AbstractContainer, layout: Layout): Unit = layout.disconnect(container)
}