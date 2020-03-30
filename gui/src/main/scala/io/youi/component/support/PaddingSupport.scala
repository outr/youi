package io.youi.component.support

import io.youi.component.Component
import io.youi.component.feature.PaddingFeature

trait PaddingSupport {
  this: Component =>

  lazy val padding: PaddingFeature = new PaddingFeature(this)
}