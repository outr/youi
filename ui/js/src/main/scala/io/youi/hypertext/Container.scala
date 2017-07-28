package io.youi.hypertext

import io.youi.dom
import org.scalajs.dom._

class Container(val element: html.Element) extends HTMLContainer with Component {
  def this() = this(dom.create[html.Element]("div"))

  // Default size to be based on children's max dimensions
  size.width := (0.0 :: children.map(_.position.right()).toList).max + scrollbar.vertical.size()
  size.height := (0.0 :: children.map(_.position.bottom()).toList).max + scrollbar.horizontal.size()

  init()
}

object Container {
  def cached(element: html.Element): Container = Component.cached[html.Element, Container](element, new Container(_))
}