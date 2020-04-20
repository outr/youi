package io.youi

sealed trait Plane

object Plane {
  case object Horizontal extends Plane
  case object Vertical extends Plane
}