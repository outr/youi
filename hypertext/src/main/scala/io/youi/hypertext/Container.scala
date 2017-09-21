package io.youi.hypertext

import io.youi.dom
import io.youi.layout.Layout
import org.scalajs.dom._
import reactify.Var

class Container(val element: html.Element) extends HTMLContainer with Component {
  def children: Var[Vector[Component]] = childEntries

  def layout: Var[Layout] = layoutManager

  def this() = this(dom.create[html.Element]("div"))

  // Default size to be based on children's max dimensions
  size.width := (0.0 :: children().map(_.position.right()).toList).max + scrollbar.vertical.size()
  size.height := (0.0 :: children().map(_.position.bottom()).toList).max + scrollbar.horizontal.size()

  init()
}

object Container {
  def cached(element: html.Element): Container = Component.cached[html.Element, Container](element, new Container(_))
}