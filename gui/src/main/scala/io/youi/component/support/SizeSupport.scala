package io.youi.component.support

import io.youi.component.Component
import io.youi.component.feature.SizeFeature

trait SizeSupport {
  this: Component =>

  lazy val size: SizeFeature = new SizeFeature(this)
}