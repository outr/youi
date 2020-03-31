package io.youi.component.feature

import io.youi.component.Component
import io.youi.component.types.SizeProperty
import reactify.Val

class SizeFeature(override val component: Component) extends Feature with WidthFeature with HeightFeature {
  override lazy val width: SizeProperty = new SizeProperty(component.element.style.width, component.element.style.width_=)
  override lazy val height: SizeProperty = new SizeProperty(component.element.style.height, component.element.style.height_=)

  lazy val center: Val[Double] = Val(width / 2.0)
  lazy val middle: Val[Double] = Val(height / 2.0)
}