package io.youi.hypertext

import org.scalajs.dom.html.Element
import io.youi.dom._

trait HTMLContainer extends AbstractContainer[Component] {
  protected[youi] val element: Element

  override protected def remove(c: Component): Unit = element.removeChild(c.element)

  override protected def addAfter(c: Component, previous: Option[Component]): Unit = previous match {
    case Some(p) => c.element.insertAfter(p.element)
    case None => c.element.insertFirst(element)
  }
}