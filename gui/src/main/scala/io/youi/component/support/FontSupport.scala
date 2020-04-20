package io.youi.component.support

import io.youi.component.Component
import io.youi.component.feature.FontFeature
import io.youi.component.types.{Prop, TextAlign, TextOverflow}

trait FontSupport {
  this: Component =>

  lazy val font: FontFeature = new FontFeature(this)
  lazy val textAlign: Prop[TextAlign] = Prop.stringify(element.style.textAlign, element.style.textAlign_=, TextAlign, TextAlign.Initial)
  lazy val textOverflow: Prop[TextOverflow] = Prop.stringify(element.style.textOverflow, element.style.textOverflow_=, TextOverflow, TextOverflow.Clip)
}