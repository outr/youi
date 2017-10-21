package io.youi

sealed trait Axis

object Axis {
  case object Horizontal extends Axis
  case object Vertical extends Axis
}