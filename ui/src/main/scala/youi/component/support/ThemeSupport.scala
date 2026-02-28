package youi.component.support

import youi.component.Component
import youi.component.feature.{FeatureParent, ThemeFeature}

trait ThemeSupport {
  this: Component =>

  lazy val theme: ThemeFeature = new ThemeFeature(this)
}