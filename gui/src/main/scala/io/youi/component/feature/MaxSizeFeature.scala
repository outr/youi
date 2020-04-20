package io.youi.component.feature

import io.youi.component.Component
import io.youi.component.types.SizeProperty
import io.youi.theme.Theme

import scala.scalajs.js.|

class MaxSizeFeature(override val parent: FeatureParent) extends Feature {
  lazy val width: SizeProperty = new SizeProperty(parent.css.maxWidth, parent.css.maxWidth_=, parent.measureComponent)
  lazy val height: SizeProperty = new SizeProperty(parent.css.maxHeight, parent.css.maxHeight_=, parent.measureComponent)
}