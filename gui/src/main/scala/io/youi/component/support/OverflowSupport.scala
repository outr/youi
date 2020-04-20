package io.youi.component.support

import io.youi.component.Component
import io.youi.component.feature.OverflowFeature

trait OverflowSupport {
  this: Component =>

  lazy val overflow: OverflowFeature = new OverflowFeature(this)
}