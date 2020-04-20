package io.youi.component.support

import io.youi.component.Component
import io.youi.component.feature.ThemeFeature

trait ThemeSupport {
  this: Component =>

  lazy val theme: ThemeFeature = new ThemeFeature(this)
}