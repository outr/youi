package io.youi.event

import io.youi.component.Component
import io.youi.spatial.Point

sealed trait HitResult

object HitResult {
  case class Hit(local: Point, component: Component) extends HitResult
  case object Miss extends HitResult
}