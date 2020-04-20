package io.youi.component.support

import io.youi.component.Component
import io.youi.component.feature.MeasuredFeature

trait MeasuredSupport {
  this: Component =>

  val measured: MeasuredFeature = new MeasuredFeature(this)
}