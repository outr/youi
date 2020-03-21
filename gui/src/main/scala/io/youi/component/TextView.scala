package io.youi.component

import io.youi.dom
import io.youi.component.support.{ContentSupport, FontSupport}
import org.scalajs.dom.html

class TextView(element: html.Element = dom.create.span) extends Component(element) with FontSupport with ContentSupport {
}
