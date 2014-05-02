package org.hyperscala.jquery.ui

import org.hyperscala.html._
import org.hyperscala.css.attributes.{Float, Display}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ErrorMessage(message: String) extends tag.Div(clazz = List("ui-widget")) {
  style.display := Display.None
  contents += new tag.Div(clazz = List("ui-state-error", "ui-corner-all")) {
    style.paddingAll(5.px)
    style.fontSize := 14.px
    contents += new tag.P {
      contents += new tag.Span(clazz = List("ui-icon", "ui-icon-alert")) {
        style.float := Float.Left
        style.marginRight := 5.px
      }
      contents += message
    }
  }
}