package io.youi.component

import io.youi.component.types.Prop
import io.youi.dom
import org.scalajs.dom.html

class Label(element: html.Label = dom.create.label) extends TextView(element) {
  lazy val `for`: Prop[String] = new Prop[String](element.htmlFor, element.htmlFor_=)
}