package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.constraints.BodyChild
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Tabs extends tag.Ul(clazz = List("nav", "nav-tabs")) with BootstrapComponent {
  val pills = boolProp("nav-pills")
  val stacked = boolProp("nav-stacked")
  val justified = boolProp("nav-justified")

  def addItem[T <: BodyChild](item: T, active: Boolean = false, disabled: Boolean = false) = {
    contents += new tag.Li {
      if (active) clazz += "active"
      if (disabled) clazz += "disabled"

      contents += item
    }
    item
  }

  def addLink(url: String, label: BodyChild, active: Boolean = false) = addItem(new tag.A(href = url, content = label), active = active)
}
