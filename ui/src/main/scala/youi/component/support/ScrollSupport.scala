package youi.component.support

import youi.component.Component
import youi.component.feature.ScrollFeature

trait ScrollSupport {
  this: Component =>

  lazy val scroll: ScrollFeature = new ScrollFeature(this)
}