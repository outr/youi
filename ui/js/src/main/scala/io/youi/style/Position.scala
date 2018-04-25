package io.youi.style

object Position {
  case object Static extends Position
  case object Relative extends Position
  case object Absolute extends Position
  case object Fixed extends Position
  case object Sticky extends Position

  def apply(value: String): Position = value.toLowerCase.trim match {
    case "relative" => Relative
    case "absolute" => Absolute
    case "fixed" => Fixed
    case "sticky" => Sticky
    case _ => Static
  }
}

sealed trait Position