package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.html.attributes.ButtonType
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.realtime._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class NavBar(brand: Option[BodyChild] = None, brandLink: Option[String] = None, theme: NavBarTheme = NavBarTheme.Light, top: Boolean = true, right: Boolean = false) extends tag.Div(clazz = List("navbar", theme.className), role = "navigation") {
  if (top) clazz += "navbar-fixed-top"

  val navigation = new tag.Ul(clazz = List("nav", "navbar-nav"))
  if (right) {
    navigation.clazz += "pull-right"
  }
  val header = new tag.Div(clazz = List("navbar-header")) {
    contents += new tag.Button(buttonType = ButtonType.Button, clazz = List("navbar-toggle")) {
      data("toggle", "collapse")
      data("target", ".navbar-collapse")
      contents += new tag.Span(clazz = List("sr-only")) {
        contents += "Toggle navigation"
      }
      contents += new tag.Span(clazz = List("icon-bar"))
      contents += new tag.Span(clazz = List("icon-bar"))
      contents += new tag.Span(clazz = List("icon-bar"))
    }
    brand match {
      case Some(brandName) => {
        contents += new tag.A(clazz = List("navbar-brand"), href = brandLink.getOrElse("#")) {
          contents += brandName
        }
      }
      case None => // No brand
    }
  }
  val bar = new tag.Div(clazz = List("navbar-collapse", "collapse")) {
    contents += navigation
  }
  val container = new tag.Div(clazz = List("container")) {
    contents += header
    contents += bar
  }
  contents += container

  def addItem[T <: BodyChild](item: T, active: Boolean = false) = {
    val listItem = new ListItem(active)
    listItem.contents += item
    navigation.contents += listItem
    listItem
  }

  def addLink(url: String, label: BodyChild, active: Boolean = false) = addItem(new tag.A(href = url, content = label), active = active)

  def addRealtime(label: BodyChild, active: Boolean = false)(f: => Unit) = {
    val listItem = addLink("#", label, active)
    listItem.clickEvent.onRealtime {
      case evt => f
    }
    listItem
  }

  def addDropdown(label: BodyChild, active: Boolean = false) = {
    val dropdown = new NavBarDropdown(this, label)
    dropdown.active := active
    navigation.contents += dropdown
    dropdown
  }
}

class NavBarTheme(val className: String) extends EnumEntry

object NavBarTheme extends Enumerated[NavBarTheme] {
  val Light = new NavBarTheme("navbar-default")
  val Dark = new NavBarTheme("navbar-inverse")
}

class NavBarDropdown(navBar: NavBar, label: BodyChild) extends ListItem {
  clazz += "dropdown"

  val menu = new tag.Ul(clazz = List("dropdown-menu"))

  contents += new tag.A(href = "#", clazz = List("dropdown-toggle")) {
    data("toggle", "dropdown")
    contents += label
    contents += new tag.B(clazz = List("caret"))
  }
  contents += menu

  def addItem[T <: BodyChild](item: T) = {
    menu.contents += new tag.Li {
      contents += item
    }
    item
  }

  def addDivider() = menu.contents += new tag.Li(clazz = List("divider"))

  def addHeader(label: String) = menu.contents += new tag.Li(clazz = List("dropdown-header"), content = label)

  def addLink(url: String, label: String, active: Boolean = false) = addItem(new tag.A(href = url, content = label))

  def addRealtime(label: String, active: Boolean = false)(f: => Unit) = addLink("#", label, active).clickEvent.onRealtime {
    case evt => f
  }
}