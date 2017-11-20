package io.youi.component.extras

import io.youi.component.Component
import io.youi.dom
import io.youi.dom._
import org.scalajs.dom.{Element, _}

trait HTMLComponent[E <: html.Element] extends Component {
  protected def element: E
  protected val e: HTMLExtras[E] = new HTMLExtras[E](element)

  parent.attachAndFire {
    case Some(p) => sibling.previous() match {
      case Some(previous) => element.insertAfter(HTMLComponent.element(previous))
      case None => element.insertFirst(HTMLComponent.element(p))
    }
    case None => element.remove()
  }
}

object HTMLComponent {
  def create[T <: Element](tagName: String): T = {
    val e = dom.create[T](tagName)
    // TODO: init
    e
  }

  def element(component: Component): html.Element = component.asInstanceOf[HTMLComponent[html.Element]].element
}