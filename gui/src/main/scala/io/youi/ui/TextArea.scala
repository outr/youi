package io.youi.ui

import io.youi.dom
import io.youi.ui.support.FontSupport
import io.youi.ui.types.Prop
import org.scalajs.dom.html

class TextArea(element: html.TextArea = dom.create.textArea) extends Component(element) with FontSupport {
  lazy val value: Prop[String] = new Prop[String](element.value, element.value_=)
}
