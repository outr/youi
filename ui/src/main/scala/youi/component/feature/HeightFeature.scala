package youi.component.feature

import youi.component.Component
import reactify.Val

trait HeightFeature extends Feature {
  def height: Val[Double]

}

object HeightFeature {
  def apply(component: Component): Option[Val[Double]] = {
    FeatureParent.get[HeightFeature]("height", component).map(_.height)
  }
}