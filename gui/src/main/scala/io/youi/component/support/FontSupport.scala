package io.youi.component.support

import io.youi.component.types.{Prop, TextAlign}
import io.youi.component.Component
import io.youi.component.feature.FontFeature

trait FontSupport {
  this: Component =>

  lazy val font: FontFeature = new FontFeature(this)
  lazy val textAlign: Prop[TextAlign] = Prop.stringify(element.style.textAlign, element.style.textAlign_=, TextAlign, TextAlign.Initial)
}