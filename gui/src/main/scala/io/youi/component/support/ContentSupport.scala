package io.youi.component.support

import io.youi.component.Component
import io.youi.component.types.Prop

trait ContentSupport {
  this: Component =>

  lazy val content: Prop[String] = new Prop[String](element.innerHTML, element.innerHTML_=, measure)
}