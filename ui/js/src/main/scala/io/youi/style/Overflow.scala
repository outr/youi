package io.youi.style

import io.youi.theme.Stringify

sealed abstract class Overflow(val value: String)

object Overflow extends Stringify[Overflow] {
  case object Auto extends Overflow("auto")
  case object Visible extends Overflow("visible")
  case object Hidden extends Overflow("hidden")
  case object Scroll extends Overflow("scroll")

  override def fromString(value: String): Option[Overflow] = value match {
    case "auto" => Some(Overflow.Auto)
    case "visible" => Some(Overflow.Visible)
    case "hidden" => Some(Overflow.Hidden)
    case "scroll" => Some(Overflow.Scroll)
  }

  override def toString(value: Overflow): Option[String] = if (value == Visible) {
    None
  } else {
    Some(value.value)
  }
}