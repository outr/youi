package youi.component.feature

import youi.component.types.SizeProperty

class MaxSizeFeature(override val parent: FeatureParent) extends Feature {
  lazy val width: SizeProperty = new SizeProperty(parent.css.maxWidth, parent.css.maxWidth_=, callbacks = parent.measureComponent)
  lazy val height: SizeProperty = new SizeProperty(parent.css.maxHeight, parent.css.maxHeight_=, callbacks = parent.measureComponent)
}