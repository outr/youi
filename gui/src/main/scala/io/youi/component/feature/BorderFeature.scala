package io.youi.component.feature

import io.youi.Stringify
import io.youi.component.Component
import io.youi.component.types.{Border, Prop}

class BorderFeature(component: Component) extends Feature(component) {
  element.style.boxSizing = "border-box"

  lazy val top: Prop[Border] = Prop.stringify(element.style.borderTop, element.style.borderTop_=, Border, Border.undefined)
  lazy val bottom: Prop[Border] = Prop.stringify(element.style.borderBottom, element.style.borderBottom_=, Border, Border.undefined)
  lazy val left: Prop[Border] = Prop.stringify(element.style.borderLeft, element.style.borderLeft_=, Border, Border.undefined)
  lazy val right: Prop[Border] = Prop.stringify(element.style.borderRight, element.style.borderRight_=, Border, Border.undefined)

  def :=(border: => Border): Unit = {
    top := border
    bottom := border
    left := border
    right := border
  }
  def @=(border: Border): Unit = :=(border)

  object radius {
    lazy val topLeft: Prop[Double] = Prop.stringify(element.style.borderTopLeftRadius, element.style.borderTopLeftRadius_=, Stringify.Pixels, 0.0)
    lazy val topRight: Prop[Double] = Prop.stringify(element.style.borderTopRightRadius, element.style.borderTopRightRadius_=, Stringify.Pixels, 0.0)
    lazy val bottomLeft: Prop[Double] = Prop.stringify(element.style.borderBottomLeftRadius, element.style.borderBottomLeftRadius_=, Stringify.Pixels, 0.0)
    lazy val bottomRight: Prop[Double] = Prop.stringify(element.style.borderBottomRightRadius, element.style.borderBottomRightRadius_=, Stringify.Pixels, 0.0)

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
