package youi.component

import youi.component.types.Prop
import youi.dom
import org.scalajs.dom.html

class LinkView(element: html.Anchor = dom.create.anchor) extends TextView(element) {
  lazy val href: Prop[String] = new Prop(element.href, element.href_=)
  lazy val target: Prop[String] = new Prop(element.target, element.target_=)
}