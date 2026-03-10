package youi.component.feature

import youi.component.types.SizeProperty
import reactify.*
import youi.spatial.Size

class SizeFeature(override val parent: FeatureParent)
                 (setWidth: String => Unit = parent.css.width_=,
                  setHeight: String => Unit = parent.css.height_=,
                  measuredWidth: Val[Double] = Val(0.0),
                  measuredHeight: Val[Double] = Val(0.0)) extends Feature with WidthFeature with HeightFeature {
  override lazy val width: SizeProperty = new SizeProperty(parent.css.width, setWidth, measuredWidth)
  override lazy val height: SizeProperty = new SizeProperty(parent.css.height, setHeight, measuredHeight)

  def @=(size: Size): Unit = {
    width @= size.width
    height @= size.height
  }

  def :=(size: => Size): Unit = {
    width := size.width
    height := size.height
  }

  lazy val center: Val[Double] = Val(width / 2.0)
  lazy val middle: Val[Double] = Val(height / 2.0)

}
