package io.youi.component.feature

import io.youi.component.Component
import reactify.Val

trait HeightFeature extends Feature {
  def height: Val[Double]

  Component.addFeature("height", component, this)
}

object HeightFeature {
  def apply(component: Component): Option[Val[Double]] = {
    Component.getFeature[HeightFeature]("height", component).map(_.height)
  }
}