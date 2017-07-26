package io.youi.hypertext.style

sealed abstract class Overflow(val value: String)

object Overflow {
  case object Unset extends Overflow("unset")
  case object Visible extends Overflow("visible")
  case object Hidden extends Overflow("hidden")
  case object Scroll extends Overflow("scroll")
  case object Auto extends Overflow("auto")
}