package io.youi.hypertext

import io.youi.dom
import org.scalajs.dom._

class Container(val element: html.Element) extends HTMLContainer with Component {
  def this() = this(dom.create[html.Element]("div"))

  init()
}

object Container {
  def cached(element: html.Element): Container = Component.cached[html.Element, Container](element, new Container(_))
}