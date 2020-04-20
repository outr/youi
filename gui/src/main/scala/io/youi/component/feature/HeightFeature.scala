package io.youi.component.feature

import io.youi.component.Component
import reactify.Val

trait HeightFeature extends Feature {
  def height: Val[Double]

  addFeature("height")
}

object HeightFeature {
  def apply(component: Component): Option[Val[Double]] = {
    FeatureParent.get[HeightFeature]("height", component).map(_.height)
  }
}