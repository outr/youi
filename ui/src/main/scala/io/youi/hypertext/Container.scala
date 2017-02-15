package io.youi.hypertext

import io.youi.dom
import org.scalajs.dom.html.Div

class Container(val element: Div) extends HTMLContainer with Component {
  def this() = this(dom.create[Div]("div"))
}

object Container {
  def cached(element: Div): Container = Component.cached[Div, Container](element, new Container(_))
}