package io.youi.component.feature

import io.youi.component.types.SizeProperty

class MinSizeFeature(override val parent: FeatureParent) extends Feature {
  lazy val width: SizeProperty = new SizeProperty(parent.css.minWidth, parent.css.minWidth_=)
  lazy val height: SizeProperty = new SizeProperty(parent.css.minHeight, parent.css.minHeight_=)
}