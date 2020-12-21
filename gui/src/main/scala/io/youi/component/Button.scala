package io.youi.component

import io.youi.component.support.FontSupport
import io.youi.component.types.Prop
import io.youi.dom
import org.scalajs.dom.html

class Button(element: html.Button = dom.create.button) extends Container(element) with FontSupport {
  lazy val disabled: Prop[Boolean] = new Prop[Boolean](element.disabled, element.disabled_=)
}