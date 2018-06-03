package io.youi.style

import io.youi.theme.Stringify

sealed abstract class PointerEvents(val value: String)

object PointerEvents extends Stringify[PointerEvents] {
  case object Auto extends PointerEvents("auto")
  case object None extends PointerEvents("none")

  override def fromString(value: String): Option[PointerEvents] = value match {
    case "none" => Some(None)
    case _ => scala.None
  }

  override def toString(value: PointerEvents): Option[String] = if (value == Auto) {
    scala.None
  } else {
    Some(value.value)
  }
}