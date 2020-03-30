package io.youi.component.support

import io.youi.component.Component
import io.youi.component.feature.OverflowFeature
import io.youi.component.types.{Overflow, Prop}

trait OverflowSupport {
  this: Component =>

  lazy val overflow: OverflowFeature = new OverflowFeature(this)
}