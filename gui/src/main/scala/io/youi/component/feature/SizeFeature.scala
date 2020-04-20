package io.youi.component.feature

import io.youi.component.Component
import io.youi.component.types.SizeProperty
import io.youi.theme.Theme
import reactify.Val

import scala.scalajs.js.|

class SizeFeature(override val parent: FeatureParent) extends Feature with WidthFeature with HeightFeature {
  override lazy val width: SizeProperty = new SizeProperty(parent.css.width, parent.css.width_=)
  override lazy val height: SizeProperty = new SizeProperty(parent.css.height, parent.css.height_=)

  lazy val center: Val[Double] = Val(width / 2.0)
  lazy val middle: Val[Double] = Val(height / 2.0)
}