package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.examples.Example
import language.reflectiveCalls
import org.powerscala.Color
import org.hyperscala.css.attributes.LineStyle
import org.hyperscala.ui.history.History

/**
 * @author Matt Hicks <matt@outr.com>
 */
class HistoryExample extends Example {
  page.require(History)

  val div = new tag.Div(id = "myDiv") {
    style.width := 200.px
    style.height := 200.px
    style.backgroundColor := Color.Red
    style.borderColor := Color.Black
    style.borderWidth := 2.px
    style.borderStyle := LineStyle.Solid
  }

  contents += div
}