package youi.component.support

import youi.component.Component
import youi.component.feature.OverflowFeature

trait OverflowSupport {
  this: Component =>

  lazy val overflow: OverflowFeature = new OverflowFeature(this)
}