package io.youi.layout

import io.youi.{AbstractComponent, AbstractContainer}
import reactify._

class VerticalBoxLayout(spacing: Double = 0.0, fillWidth: Boolean = false, fillWidthAdjust: Int = 2) extends Layout with (Vector[AbstractComponent] => Unit) {
  override protected def connect[C <: AbstractComponent](container: AbstractContainer[C]): Unit = {
    container.children.attachAndFire(this)
  }

  override protected def disconnect[C <: AbstractComponent](container: AbstractContainer[C]): Unit = {
    container.children.detach(this)
  }

  override def apply(children: Vector[AbstractComponent]): Unit = if (children.nonEmpty) {
    val parent = children.head.parent().get
    recurse(parent, None, children)
  }

  private def recurse(parent: AbstractComponent, previous: Option[AbstractComponent], children: Vector[AbstractComponent]): Unit = if (children.nonEmpty) {
    val child = children.head
    previous match {
      case Some(p) => {
        child.position.top := p.position.bottom + spacing
      }
      case None => {
        child.position.top := 0.0
      }
    }
    if (fillWidth) {
      child.size.width := parent.size.width - fillWidthAdjust - parent.scrollbar.vertical.size
    }
    recurse(parent, Some(child), children.tail)
  }
}
