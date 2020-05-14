package io.youi.component.feature

import io.youi.component.types.SizeProperty
import reactify.Val

class SizeFeature(override val parent: FeatureParent)
                 (setWidth: String => Unit = parent.css.width_=,
                  setHeight: String => Unit = parent.css.height_=) extends Feature with WidthFeature with HeightFeature {
  override lazy val width: SizeProperty = new SizeProperty(parent.css.width, setWidth)
  override lazy val height: SizeProperty = new SizeProperty(parent.css.height, setHeight)

  lazy val center: Val[Double] = Val(width / 2.0)
  lazy val middle: Val[Double] = Val(height / 2.0)
}