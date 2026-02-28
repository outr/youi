package youi.component.support

import youi.component.Component
import youi.component.feature.PaddingFeature

trait PaddingSupport {
  this: Component =>

  lazy val padding: PaddingFeature = new PaddingFeature(this)
}