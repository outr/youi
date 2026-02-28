package youi.component

import youi.component.types.Prop
import youi.dom
import org.scalajs.dom.html

class Label(element: html.Label = dom.create.label) extends TextView(element) {
  lazy val `for`: Prop[String] = new Prop[String](element.htmlFor, element.htmlFor_=)
}