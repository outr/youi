package io.youi.gui.support

import io.youi.gui.Component
import io.youi.gui.types.Prop

trait MarginSupport {
  this: Component =>

  object margin {
    lazy val left: Prop[Double] = new Prop[Double](0.0, d => element.style.marginLeft = d.toString)
    lazy val right: Prop[Double] = new Prop[Double](0.0, d => element.style.marginRight = d.toString)
    lazy val top: Prop[Double] = new Prop[Double](0.0, d => element.style.marginTop = d.toString)
    lazy val bottom: Prop[Double] = new Prop[Double](0.0, d => element.style.marginBottom = d.toString)
  }
}
