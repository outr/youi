package io.youi.component.feature

import io.youi.component.types.Prop

class MarginFeature(override val parent: FeatureParent) extends Feature {
  lazy val left: Prop[Double] = new Prop[Double](-1.0, d => parent.css.marginLeft = d.toString)
  lazy val right: Prop[Double] = new Prop[Double](-1.0, d => parent.css.marginRight = d.toString)
  lazy val top: Prop[Double] = new Prop[Double](-1.0, d => parent.css.marginTop = d.toString)
  lazy val bottom: Prop[Double] = new Prop[Double](-1.0, d => parent.css.marginBottom = d.toString)

  def :=(value: => Double): Unit = {
    left := value
    right := value
    top := value
    bottom := value
  }

  def @=(value: Double): Unit = this := value
}