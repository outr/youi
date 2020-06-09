package io.youi.component.support

import io.youi.component.Component
import io.youi.component.feature.ScrollFeature

trait ScrollSupport {
  this: Component =>

  lazy val scroll: ScrollFeature = new ScrollFeature(this)
}