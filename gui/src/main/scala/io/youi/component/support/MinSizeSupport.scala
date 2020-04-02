package io.youi.component.support

import io.youi.component.Component
import io.youi.component.feature.MinSizeFeature

trait MinSizeSupport {
  this: Component =>

  val minSize: MinSizeFeature = new MinSizeFeature(this)
}