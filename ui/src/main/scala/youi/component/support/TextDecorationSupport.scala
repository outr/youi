package youi.component.support

import youi.component.Component
import youi.component.feature.TextDecorationFeature

trait TextDecorationSupport {
  this: Component =>

  lazy val textDecoration: TextDecorationFeature = new TextDecorationFeature(this)
}