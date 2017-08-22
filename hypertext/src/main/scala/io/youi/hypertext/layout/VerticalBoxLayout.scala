package io.youi.hypertext.layout

import io.youi.hypertext.{AbstractComponent, AbstractContainer}
import reactify._

class VerticalBoxLayout(spacing: Double = 0.0,
                        fillWidth: Boolean = false,
                        fillWidthAdjust: Int = 2,
                        fromBottom: Boolean = false) extends BasicLayout {
  override protected def layout(parent: AbstractContainer[AbstractComponent], children: Vector[AbstractComponent]): Unit = {
    if (fromBottom) {
      recurse(parent, None, children.reverse)
    } else {
      recurse(parent, None, children)
    }
  }

  private def recurse(parent: AbstractComponent, previous: Option[AbstractComponent], children: Vector[AbstractComponent]): Unit = if (children.nonEmpty) {
    val child = children.head
    previous match {
      case Some(p) => if (fromBottom) {
        child.position.bottom := p.position.top - spacing
      } else {
        child.position.top := p.position.bottom + spacing
      }
      case None => if (fromBottom) {
        child.position.bottom := parent.size.height
      } else {
        child.position.top := 0.0
      }
    }
    if (fillWidth) {
      child.size.width := parent.size.width - fillWidthAdjust - parent.scrollbar.vertical.size
    }
    recurse(parent, Some(child), children.tail)
  }
}
