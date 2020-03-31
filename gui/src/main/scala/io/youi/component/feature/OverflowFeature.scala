package io.youi.component.feature

import io.youi.component.Component
import io.youi.component.types.{Overflow, Prop}

class OverflowFeature(override val component: Component) extends Feature {
  lazy val x: Prop[Overflow] = Prop.stringify(component.element.style.overflowX, component.element.style.overflowX_=, Overflow, Overflow.Visible)
  lazy val y: Prop[Overflow] = Prop.stringify(component.element.style.overflowY, component.element.style.overflowY_=, Overflow, Overflow.Visible)

  def :=(overflow: => Overflow): Unit = {
    x := overflow
    y := overflow
  }

  def @=(overflow: Overflow): Unit = {
    x @= overflow
    y @= overflow
  }
}