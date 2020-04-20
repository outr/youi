package io.youi.component.support

import io.youi.component.Component
import io.youi.component.feature.PositionFeature

trait PositionSupport {
  this: Component =>

  lazy val position: PositionFeature = new PositionFeature(this)
}