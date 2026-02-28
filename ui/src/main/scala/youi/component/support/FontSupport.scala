package youi.component.support

import youi.component.Component
import youi.component.feature.FontFeature
import youi.component.types.{Prop, TextAlign, TextOverflow}

trait FontSupport {
  this: Component =>

  lazy val font: FontFeature = new FontFeature(this)
  lazy val textAlign: Prop[TextAlign] = Prop.stringify(element.style.textAlign, element.style.textAlign_=, TextAlign, TextAlign.Initial)
  lazy val textOverflow: Prop[TextOverflow] = Prop.stringify(element.style.textOverflow, element.style.textOverflow_=, TextOverflow, TextOverflow.Clip)
}