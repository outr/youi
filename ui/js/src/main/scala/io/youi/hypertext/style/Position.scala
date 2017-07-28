package io.youi.hypertext.style

object Position {
  case object Static extends Position
  case object Relative extends Position
  case object Absolute extends Position
  case object Fixed extends Position
  case object Sticky extends Position
}

sealed trait Position