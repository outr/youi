package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.selector.PseudoClass
import org.hyperscala.ui.module.ExternalStyle
import org.hyperscala.web._
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ExternalStyleExample extends Webpage with Example {
  require(ExternalStyle)

  body.contents += new tag.Div(id = "testDiv") {
    style.width := 150.px
    style.height := 150.px
    style.backgroundColor := Color.Red
    connected[tag.HTML] {
      case html => {
        val hoverStyle = style(PseudoClass.Hover)
        hoverStyle.backgroundColor := Color.Green
      }
    }
  }
}
