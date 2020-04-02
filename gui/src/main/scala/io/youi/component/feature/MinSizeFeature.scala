package io.youi.component.feature

import io.youi.component.Component
import io.youi.component.types.SizeProperty

class MinSizeFeature(override val component: Component) extends Feature {
  lazy val width: SizeProperty = new SizeProperty(component.element.style.minWidth, component.element.style.minWidth_=)
  lazy val height: SizeProperty = new SizeProperty(component.element.style.minHeight, component.element.style.minHeight_=)
}