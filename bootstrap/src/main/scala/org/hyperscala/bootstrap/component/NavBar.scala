package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.extension.{ClassProperty, ClassName, ClassBooleanProperty}
import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.html.attributes.ButtonType
import org.hyperscala.html.constraints.BodyChild

/**
 * @author Matt Hicks <matt@outr.com>
 */
class NavBar extends tag.Div(clazz = List("navbar"), role = "navigation") {
  val top = new ClassBooleanProperty(this, enabled = Some("navbar-fixed-top"))
  val theme = new ClassProperty[NavBarTheme](this, NavBarTheme.Default)
}

sealed abstract class NavBarTheme(val className: Option[String])
  extends EnumEntry with ClassName

object NavBarTheme extends Enumerated[AlertType] {
  case object Default extends NavBarTheme(Some("navbar-default"))
  case object Light extends NavBarTheme(Some("navbar-light"))
  case object Inverse extends NavBarTheme(Some("navbar-inverse"))

  val values = findValues.toVector
}

class NavBarDropdown(body: BodyChild*) extends ListItem {
  clazz += "dropdown"
  body.foreach(contents += _)
}

class NavBarNav extends tag.Ul(clazz = List("nav", "navbar-nav")) {
  val pullRight = new ClassBooleanProperty(this, enabled = Some("pull-right"))
}

class NavbarBrand extends tag.A(clazz = List("navbar-brand"))

class NavBarHeader extends tag.Div(clazz = List("navbar-header"))

class NavBarCollapse extends tag.Div(clazz = List("navbar-collapse", "collapse"))

class NavBarToggle extends tag.Button(buttonType = ButtonType.Button, clazz = List("navbar-toggle")) {
  data("toggle", "collapse")
  data("target", ".navbar-collapse")
}

class Caret extends tag.B(clazz = List("caret"))

class Divider extends tag.Li(clazz = List("divider"))
