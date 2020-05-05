package io.youi.component.types

import io.youi.Stringify

sealed abstract class PointerEvents(val value: String)

object PointerEvents extends Stringify[PointerEvents] {
  case object Auto extends PointerEvents("auto")
  case object None extends PointerEvents("none")
  case object Inherit extends PointerEvents("inherit")
  case object Initial extends PointerEvents("initial")
  case object Unset extends PointerEvents("")

  private val list: List[PointerEvents] = List(None, Auto, Inherit, Initial, Unset)
  private val map: Map[String, PointerEvents] = list.map(us => us.value -> us).toMap

  override def fromString(value: String): Option[PointerEvents] = map.get(value)

  override def toString(value: PointerEvents): Option[String] = Some(value.value)
}