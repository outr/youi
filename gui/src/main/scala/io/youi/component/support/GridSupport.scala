package io.youi.component.support

import io.youi.component.Component
import io.youi.component.feature.ColumnFeature

trait GridSupport[Child <: Component] extends TypedContainerSupport[Child] {
  lazy val column: ColumnFeature = new ColumnFeature(this)
}