package io.youi.ui

import io.youi.dom
import io.youi.ui.support.FontSupport
import io.youi.ui.types.Prop
import org.scalajs.dom.html

class TextInput(element: html.Input = dom.create.input) extends Component(element) with FontSupport {
  lazy val value: Prop[String] = new Prop[String](element.value, element.value_=)
}