package io.youi.component.support

import io.youi.component.Component
import io.youi.component.feature.BorderFeature

trait BorderSupport {
  this: Component =>

  val border: BorderFeature = new BorderFeature(this)
}