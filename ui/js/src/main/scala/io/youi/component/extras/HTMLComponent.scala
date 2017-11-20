package io.youi.component.extras

import io.youi.component.Component
import io.youi.dom
import org.scalajs.dom.{Element, _}

trait HTMLComponent extends Component {
  protected def element: html.Element
  protected val e: HTMLExtras = new HTMLExtras(element)
}

object HTMLComponent {
  def create[T <: Element](tagName: String): T = {
    val e = dom.create[T](tagName)
    // TODO: init
    e
  }
}