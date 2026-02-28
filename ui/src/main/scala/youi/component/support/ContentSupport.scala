package youi.component.support

import youi.component.Component
import youi.component.types.Prop

trait ContentSupport {
  this: Component =>

  lazy val content: Prop[String] = new Prop[String](element.innerHTML, element.innerHTML_=, measure.trigger)
}