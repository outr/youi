package io.youi.component.feature

import io.youi.component.Component
import io.youi.component.types.SizeProperty

class MaxSizeFeature(override val component: Component) extends Feature {
  lazy val width: SizeProperty = new SizeProperty(component.element.style.maxWidth, component.element.style.maxWidth_=)
  lazy val height: SizeProperty = new SizeProperty(component.element.style.maxHeight, component.element.style.maxHeight_=)
}