package org.hyperscala.jquery.ui

import org.hyperscala.html._
import org.hyperscala.css.attributes._

/**
  * @author Matt Hicks <matt@outr.com>
  */
class HighlightMessage(message: String) extends tag.Div(clazz = List("ui-widget")) {
   style.display := Display.None
   contents += new tag.Div(clazz = List("ui-state-highlight", "ui-corner-all")) {
     style.paddingAll(5.px)
     style.fontSize := 14.px
     contents += new tag.P {
       contents += new tag.Span(clazz = List("ui-icon", "ui-icon-info")) {
         style.float := Float.Left
         style.marginRight := 5.px
       }
       contents += message
     }
   }
 }