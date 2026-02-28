package youi.component.support

import youi.component.Component
import youi.component.feature.PreferredSizeFeature

trait PreferredSizeSupport {
  this: Component =>

  val preferred: PreferredSizeFeature = new PreferredSizeFeature(this)
}