package io.youi.component.feature

import io.youi.Stringify
import io.youi.component.Component
import io.youi.component.types.{Border, Prop}
import io.youi.theme.Theme

import scala.scalajs.js.|

class BorderFeature(override val parent: FeatureParent) extends Feature {
  parent.css.boxSizing = "border-box"

  lazy val top: Prop[Border] = Prop.stringify(parent.css.borderTop, parent.css.borderTop_=, Border, Border.undefined)
  lazy val bottom: Prop[Border] = Prop.stringify(parent.css.borderBottom, parent.css.borderBottom_=, Border, Border.undefined)
  lazy val left: Prop[Border] = Prop.stringify(parent.css.borderLeft, parent.css.borderLeft_=, Border, Border.undefined)
  lazy val right: Prop[Border] = Prop.stringify(parent.css.borderRight, parent.css.borderRight_=, Border, Border.undefined)

  def :=(border: => Border): Unit = {
    top := border
    bottom := border
    left := border
    right := border
  }
  def @=(border: Border): Unit = :=(border)

  object radius {
    lazy val topLeft: Prop[Double] = Prop.stringify(parent.css.borderTopLeftRadius, parent.css.borderTopLeftRadius_=, Stringify.Pixels, 0.0)
    lazy val topRight: Prop[Double] = Prop.stringify(parent.css.borderTopRightRadius, parent.css.borderTopRightRadius_=, Stringify.Pixels, 0.0)
    lazy val bottomLeft: Prop[Double] = Prop.stringify(parent.css.borderBottomLeftRadius, parent.css.borderBottomLeftRadius_=, Stringify.Pixels, 0.0)
    lazy val bottomRight: Prop[Double] = Prop.stringify(parent.css.borderBottomRightRadius, parent.css.borderBottomRightRadius_=, Stringify.Pixels, 0.0)

    def :=(value: => Double): Unit = {
      topLeft := value
      topRight := value
      bottomLeft := value
      bottomRight := value
    }
    def @=(value: Double): Unit = :=(value)

    object top {
      def :=(value: => Double): Unit = {
        topLeft := value
        topRight := value
      }
      def @=(value: Double): Unit = :=(value)
    }
    object bottom {
      def :=(value: => Double): Unit = {
        bottomLeft := value
        bottomRight := value
      }
      def @=(value: Double): Unit = :=(value)
    }
    object left {
      def :=(value: => Double): Unit = {
        topLeft := value
        bottomLeft := value
      }
      def @=(value: Double): Unit = :=(value)
    }
    object right {
      def :=(value: => Double): Unit = {
        topRight := value
        bottomRight := value
      }
      def @=(value: Double): Unit = :=(value)
    }
  }
}
