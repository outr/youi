package io.youi.component.support

import io.youi.component.Component
import io.youi.component.types.Prop

trait MarginSupport {
  this: Component =>

  object margin {
    lazy val left: Prop[Double] = new Prop[Double](0.0, d => element.style.marginLeft = d.toString)
    lazy val right: Prop[Double] = new Prop[Double](0.0, d => element.style.marginRight = d.toString)
    lazy val top: Prop[Double] = new Prop[Double](0.0, d => element.style.marginTop = d.toString)
    lazy val bottom: Prop[Double] = new Prop[Double](0.0, d => element.style.marginBottom = d.toString)
  }
}
