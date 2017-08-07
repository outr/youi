package io.youi.style

import io.youi.component.Component
import io.youi.spatial.Point

sealed trait GradientDirection {
  def info(component: Component): GradientDirectionInfo
}

object GradientDirection {
  case object Vertical extends GradientDirection {
    override def info(component: Component): GradientDirectionInfo = GradientDirectionInfo(
      Point.zero,
      Point(y = component.size.height())
    )
  }
  case object Horizontal extends GradientDirection {
    override def info(component: Component): GradientDirectionInfo = GradientDirectionInfo(
      Point.zero,
      Point(x = component.size.width())
    )
  }
  def apply(begin: Point, end: Point): GradientDirection = GradientDirectionInfo(begin, end)
}

case class GradientDirectionInfo(begin: Point, end: Point) extends GradientDirection {
  override def info(component: Component): GradientDirectionInfo = this
}