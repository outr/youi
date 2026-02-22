package io.youi.component.feature

import io.youi.component.Component
import reactify.Val

trait WidthFeature extends Feature {
  def width: Val[Double]

}

object WidthFeature {
  def apply(component: Component): Option[Val[Double]] = {
    FeatureParent.get[WidthFeature]("width", component).map(_.width)
  }
}