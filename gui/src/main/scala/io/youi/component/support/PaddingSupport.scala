package io.youi.component.support

import io.youi.component.Component
import io.youi.component.types.Prop

trait PaddingSupport {
  this: Component =>

  object padding {
    lazy val left: Prop[Double] = new Prop[Double](-1.0, d => element.style.paddingLeft = d.toString)
    lazy val right: Prop[Double] = new Prop[Double](-1.0, d => element.style.paddingRight = d.toString)
    lazy val top: Prop[Double] = new Prop[Double](-1.0, d => element.style.paddingTop = d.toString)
    lazy val bottom: Prop[Double] = new Prop[Double](-1.0, d => element.style.paddingBottom = d.toString)

    def :=(value: => Double): Unit = {
      left := value
      right := value
      top := value
      bottom := value
    }

    def @=(value: Double): Unit = this := value
  }
}