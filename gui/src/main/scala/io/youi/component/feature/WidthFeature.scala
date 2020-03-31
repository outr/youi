package io.youi.component.feature

import io.youi.component.Component
import reactify.Val

trait WidthFeature extends Feature {
  def width: Val[Double]

  Component.addFeature("width", component, this)
}

object WidthFeature {
  def apply(component: Component): Option[Val[Double]] = {
    Component.getFeature[WidthFeature]("width", component).map(_.width)
  }
}