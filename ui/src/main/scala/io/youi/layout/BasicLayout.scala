package io.youi.layout

import io.youi.{AbstractComponent, AbstractContainer}

abstract class BasicLayout(updateOnParentResize: Boolean = false,
                           updateOnChildResize: Boolean = false) extends Layout with (Vector[AbstractComponent] => Unit) {
  private var parent: Option[AbstractContainer[AbstractComponent]] = None

  private lazy val resizeListener = (d: Double) => {
    layout(parent.get, parent.get.children())
  }

  private var childrenMonitored: Set[AbstractComponent] = Set.empty

  override protected def connect[C <: AbstractComponent](container: AbstractContainer[C]): Unit = {
    if (parent.nonEmpty) throw new RuntimeException(s"This layout is already attached to another container (${parent.get}). A Layout instance can only be connected to one container.")
    container.children.attachAndFire(this)
    if (updateOnParentResize) {
      container.size.actual.width.attach(resizeListener)
      container.size.actual.height.attach(resizeListener)
    }
    if (updateOnChildResize) {
      val children = container.children().asInstanceOf[Vector[AbstractComponent]].toSet
      childrenMonitored.foreach { child =>
        if (!children.contains(child)) {            // Remove children from previous monitored state
          child.size.actual.width.detach(resizeListener)
          child.size.actual.height.detach(resizeListener)
        }
      }
      children.foreach { child =>
        if (!childrenMonitored.contains(child)) {
          child.size.actual.width.attach(resizeListener)
          child.size.actual.height.attach(resizeListener)
        }
      }
      childrenMonitored = children
    }
    parent = Some(container.asInstanceOf[AbstractContainer[AbstractComponent]])
  }

  override protected def disconnect[C <: AbstractComponent](container: AbstractContainer[C]): Unit = {
    container.children.detach(this)
    if (updateOnParentResize) {
      container.size.actual.width.detach(resizeListener)
      container.size.actual.height.detach(resizeListener)
    }
    if (updateOnChildResize) {
      childrenMonitored.foreach { child =>
        child.size.actual.width.detach(resizeListener)
        child.size.actual.height.detach(resizeListener)
      }
      childrenMonitored = Set.empty
    }
    parent = None
  }

  override def apply(children: Vector[AbstractComponent]): Unit = if (children.nonEmpty) {
    layout(parent.get, children)
  }

  protected def layout(parent: AbstractContainer[AbstractComponent], children: Vector[AbstractComponent]): Unit
}
