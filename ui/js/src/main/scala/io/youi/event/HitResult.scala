package io.youi.event

sealed trait HitResult

object HitResult {
  case class Hit(local: Point, component: Component) extends HitResult
  case object Miss extends HitResult
}