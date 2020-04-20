package io.youi.component.feature

import io.youi.component.Component
import io.youi.component.types.Prop
import io.youi.theme.Theme

import scala.scalajs.js.|

class PaddingFeature(override val parent: FeatureParent) extends Feature {
  lazy val left: Prop[Double] = new Prop[Double](-1.0, d => parent.css.paddingLeft = d.toString)
  lazy val right: Prop[Double] = new Prop[Double](-1.0, d => parent.css.paddingRight = d.toString)
  lazy val top: Prop[Double] = new Prop[Double](-1.0, d => parent.css.paddingTop = d.toString)
  lazy val bottom: Prop[Double] = new Prop[Double](-1.0, d => parent.css.paddingBottom = d.toString)

  def :=(value: => Double): Unit = {
    left := value
    right := value
    top := value
    bottom := value
  }

  def @=(value: Double): Unit = this := value
}