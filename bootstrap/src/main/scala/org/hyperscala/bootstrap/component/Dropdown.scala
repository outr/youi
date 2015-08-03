package org.hyperscala.bootstrap.component

import org.hyperscala.html._

class DropdownMenu extends tag.Ul(clazz = List("dropdown-menu"))

class DropdownToggle extends tag.A(href = "#", clazz = List("dropdown-toggle")) {
  data("toggle", "dropdown")
}

class DropdownHeader extends tag.Li(clazz = List("dropdown-header"))
