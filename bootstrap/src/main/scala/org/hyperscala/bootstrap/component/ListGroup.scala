package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.constraints.BodyChild

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ListGroup extends tag.Div(clazz = List("list-group")) {
  def addItem[T <: BodyChild](item: T, active: Boolean = false) = {
    item.clazz += "list-group-item"
    if (active) {
      item.clazz += "active"
    }

    contents += item
    item
  }

  def addLink(url: String, label: BodyChild, active: Boolean = false) = addItem(new tag.A(href = url, content = label), active = active)

  def addText(label: String, active: Boolean = false) = addItem(new tag.Span(content = label), active = active)

  def add(url: String, heading: String, content: String, active: Boolean = false) = {
    val l = addLink(url, null, active = active)
    l.contents += new tag.H4(clazz = List("list-group-item-heading"), content = heading)
    l.contents += new tag.P(clazz = List("list-group-item-text"), content = content)
  }
}