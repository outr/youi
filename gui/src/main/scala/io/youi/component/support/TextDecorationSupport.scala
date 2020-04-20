package io.youi.component.support

import io.youi.component.Component
import io.youi.component.feature.TextDecorationFeature

trait TextDecorationSupport {
  this: Component =>

  lazy val textDecoration: TextDecorationFeature = new TextDecorationFeature(this)
}