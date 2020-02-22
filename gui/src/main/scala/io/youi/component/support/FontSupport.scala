package io.youi.component.support

import io.youi.component.types.{Prop, SizeProperty}
import io.youi.component.Component

trait FontSupport {
  this: Component =>

  object font {
    lazy val family: Prop[String] = new Prop[String](element.style.fontFamily, element.style.fontFamily_=, measure)
    lazy val weight: Prop[String] = new Prop[String](element.style.fontWeight, element.style.fontWeight_=, measure)
    lazy val size: SizeProperty = new SizeProperty(element.style.fontSize, element.style.fontSize_=, measure)
  }
}
