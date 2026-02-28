package youi.component.support

import youi.component.Component
import youi.component.feature.MaxSizeFeature

trait MaxSizeSupport {
  this: Component =>

  val maxSize: MaxSizeFeature = new MaxSizeFeature(this)
}