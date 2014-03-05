package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.examples.Example
import org.powerscala.Color
import org.hyperscala.ui.module.ExternalStyle
import org.hyperscala.selector.PseudoClass

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ExternalStyleExample extends Example {
  page.require(ExternalStyle)

  contents += new tag.Div(id = "testDiv") {
    style.width := 150.px
    style.height := 150.px
    style.backgroundColor := Color.Red
    val hoverStyle = style(PseudoClass.Hover)
    hoverStyle.backgroundColor := Color.Green
  }
}
