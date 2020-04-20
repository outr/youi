package io.youi.component.support

import io.youi.component.Component
import io.youi.component.feature.MaxSizeFeature

trait MaxSizeSupport {
  this: Component =>

  val maxSize: MaxSizeFeature = new MaxSizeFeature(this)
}