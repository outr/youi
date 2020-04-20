package io.youi.component.feature

import io.youi.component.Component
import io.youi.component.types.{Overflow, Prop}
import io.youi.theme.Theme

import scala.scalajs.js.|

class OverflowFeature(override val parent: FeatureParent) extends Feature {
  lazy val x: Prop[Overflow] = Prop.stringify(parent.css.overflowX, parent.css.overflowX_=, Overflow, Overflow.Visible)
  lazy val y: Prop[Overflow] = Prop.stringify(parent.css.overflowY, parent.css.overflowY_=, Overflow, Overflow.Visible)

  def :=(overflow: => Overflow): Unit = {
    x := overflow
    y := overflow
  }

  def @=(overflow: Overflow): Unit = {
    x @= overflow
    y @= overflow
  }
}