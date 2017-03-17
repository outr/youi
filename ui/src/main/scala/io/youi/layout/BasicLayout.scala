package io.youi.layout

import io.youi.{AbstractComponent, AbstractContainer}

trait BasicLayout extends Layout with (Vector[AbstractComponent] => Unit) {
  override protected def connect[C <: AbstractComponent](container: AbstractContainer[C]): Unit = {
    container.children.attachAndFire(this)
  }

  override protected def disconnect[C <: AbstractComponent](container: AbstractContainer[C]): Unit = {
    container.children.detach(this)
  }

  override def apply(children: Vector[AbstractComponent]): Unit = if (children.nonEmpty) {
    val parent = children.head.parent().get.asInstanceOf[AbstractContainer[AbstractComponent]]
    layout(parent, children)
  }

  protected def layout(parent: AbstractContainer[AbstractComponent], children: Vector[AbstractComponent]): Unit
}
