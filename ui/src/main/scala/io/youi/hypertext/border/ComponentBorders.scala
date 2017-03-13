package io.youi.hypertext.border

import io.youi.Color
import io.youi.hypertext.Component
import reactify.Var

class ComponentBorders(component: Component) {
  import component._

  private lazy val topLeftRadius = prop[Double](0.0, d => element.style.borderTopLeftRadius = s"${d}px", mayCauseResize = true)
  private lazy val topRightRadius = prop[Double](0.0, d => element.style.borderTopRightRadius = s"${d}px", mayCauseResize = true)
  private lazy val bottomLeftRadius = prop[Double](0.0, d => element.style.borderBottomLeftRadius = s"${d}px", mayCauseResize = true)
  private lazy val bottomRightRadius = prop[Double](0.0, d => element.style.borderBottomRightRadius = s"${d}px", mayCauseResize = true)

  case object top extends Border(component, element.style.borderTopColor = _, element.style.borderTopStyle = _, element.style.borderTopWidth = _) {
    object radius {
      def :=(value: Double): Unit = set(value)
      def set(value: Double): Unit = {
        left.radius := value
        right.radius := value
      }
    }
    object left {
      def radius: Var[Double] = topLeftRadius
    }
    object right {
      def radius: Var[Double] = topRightRadius
    }
  }
  case object bottom extends Border(component, element.style.borderBottomColor = _, element.style.borderBottomStyle = _, element.style.borderBottomWidth = _) {
    object radius {
      def :=(value: Double): Unit = set(value)
      def set(value: Double): Unit = {
        left.radius := value
        right.radius := value
      }
    }
    object left {
      def radius: Var[Double] = bottomLeftRadius
    }
    object right {
      def radius: Var[Double] = bottomRightRadius
    }
  }
  case object left extends Border(component, element.style.borderLeftColor = _, element.style.borderLeftStyle = _, element.style.borderLeftWidth = _) {
    object radius {
      def :=(value: Double): Unit = set(value)
      def set(value: Double): Unit = {
        top.radius := value
        bottom.radius := value
      }
    }
    object top {
      def radius: Var[Double] = topLeftRadius
    }
    object bottom {
      def radius: Var[Double] = bottomLeftRadius
    }
  }
  case object right extends Border(component, element.style.borderRightColor = _, element.style.borderRightStyle = _, element.style.borderRightWidth = _) {
    object radius {
      def :=(value: Double): Unit = set(value)
      def set(value: Double): Unit = {
        top.radius := value
        bottom.radius := value
      }
    }
    object top {
      def radius: Var[Double] = topRightRadius
    }
    object bottom {
      def radius: Var[Double] = bottomRightRadius
    }
  }

  object color {
    def :=(value: Option[Color]): Unit = set(value)
    def set(value: Option[Color]): Unit = {
      top.color := value
      bottom.color := value
      left.color := value
      right.color := value
    }
  }
  object style {
    def :=(value: Option[BorderStyle]): Unit = set(value)
    def set(value: Option[BorderStyle]): Unit = {
      top.style := value
      bottom.style := value
      left.style := value
      right.style := value
    }
  }
  object width {
    def :=(value: Option[Double]): Unit = set(value)
    def set(value: Option[Double]): Unit = {
      top.width := value
      bottom.width := value
      left.width := value
      right.width := value
    }
  }
  object radius {
    def :=(value: Double): Unit = set(value)
    def set(value: Double): Unit = {
      topLeftRadius := value
      topRightRadius := value
      bottomLeftRadius := value
      bottomRightRadius := value
    }
  }

  def clear(): Unit = {
    color := Some(Color.Clear)
    style := None
    width := Some(0.0)
    radius := 0.0
  }
}
