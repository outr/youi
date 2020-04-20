package io.youi.theme

sealed abstract class ElementState(val value: String)

object ElementState {
  case object Default extends ElementState("")
  case object Active extends ElementState("active")
  case object Focus extends ElementState("focus")
  case object FocusWithin extends ElementState("focus-within")
  case object Hover extends ElementState("hover")
  case object Visited extends ElementState("visited")
}