package youi.component.support

import youi.component.Component
import youi.component.feature.MinSizeFeature

trait MinSizeSupport {
  this: Component =>

  val minSize: MinSizeFeature = new MinSizeFeature(this)
}