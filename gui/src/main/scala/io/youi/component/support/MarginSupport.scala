package io.youi.component.support

import io.youi.component.Component
import io.youi.component.feature.MarginFeature
import io.youi.component.types.Prop

trait MarginSupport {
  this: Component =>

  lazy val margin: MarginFeature = new MarginFeature(this)
}