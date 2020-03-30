package io.youi.component.feature

import io.youi.component.Component
import io.youi.component.types.{Overflow, Prop}

class OverflowFeature(component: Component) extends Feature(component) {
  lazy val x: Prop[Overflow] = Prop.stringify(element.style.overflowX, element.style.overflowX_=, Overflow, Overflow.Visible)
  lazy val y: Prop[Overflow] = Prop.stringify(element.style.overflowY, element.style.overflowY_=, Overflow, Overflow.Visible)

  def :=(overflow: => Overflow): Unit = {
    x := overflow
    y := overflow
  }

  def @=(overflow: Overflow): Unit = {
    x @= overflow
    y @= overflow
  }
}