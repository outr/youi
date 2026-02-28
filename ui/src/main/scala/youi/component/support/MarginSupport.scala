package youi.component.support

import youi.component.Component
import youi.component.feature.MarginFeature

trait MarginSupport {
  this: Component =>

  lazy val margin: MarginFeature = new MarginFeature(this)
}