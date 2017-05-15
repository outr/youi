package io.youi.style

sealed trait GradientDirection

object GradientDirection {
  case object Vertical extends GradientDirection
  case object Horizontal extends GradientDirection
}