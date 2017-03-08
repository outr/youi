package io.youi.layout

import io.youi.{AbstractComponent, AbstractContainer}

/**
  * Layouts can be defined and connected to an AbstractContainer to manage the layout of the children. By default no
  * layout is defined and each child is responsible for its own positioning and sizing.
  */
trait Layout {
  protected def connect[C <: AbstractComponent](container: AbstractContainer[C]): Unit

  protected def disconnect[C <: AbstractComponent](container: AbstractContainer[C]): Unit
}

object Layout {
  private[youi] def connect[C <: AbstractComponent](container: AbstractContainer[C], layout: Layout): Unit = {
    layout.connect(container)
  }

  private[youi] def disconnect[C <: AbstractComponent](container: AbstractContainer[C], layout: Layout): Unit = {
    layout.disconnect(container)
  }
}