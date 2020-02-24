package io.youi.component.support

import io.youi.component.Component
import io.youi.component.types.{Overflow, Prop}

trait OverflowSupport {
  this: Component =>

  object overflow {
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
}