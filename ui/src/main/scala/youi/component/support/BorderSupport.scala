package youi.component.support

import youi.component.Component
import youi.component.feature.BorderFeature

trait BorderSupport {
  this: Component =>

  lazy val border: BorderFeature = new BorderFeature(this)
}