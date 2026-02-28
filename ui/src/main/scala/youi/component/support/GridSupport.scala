package youi.component.support

import youi.component.Component
import youi.component.feature.ColumnFeature

trait GridSupport[Child <: Component] extends TypedContainerSupport[Child] {
  lazy val column: ColumnFeature = new ColumnFeature(this)
}