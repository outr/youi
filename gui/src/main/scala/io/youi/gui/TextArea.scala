package io.youi.gui

import io.youi.dom
import io.youi.gui.support.FontSupport
import io.youi.gui.types.Prop
import org.scalajs.dom.html

class TextArea(element: html.TextArea = dom.create.textArea) extends Component(element) with FontSupport {
  lazy val value: Prop[String] = new Prop[String](element.value, element.value_=)
}
