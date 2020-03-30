package io.youi.component.support

import io.youi.component.Component
import io.youi.component.feature.PreferredSizeFeature

trait PreferredSizeSupport {
  this: Component =>

  lazy val preferred: PreferredSizeFeature = new PreferredSizeFeature(this)
}