package io.youi.style

object Position extends Stringify[Position] {
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

  override def fromString(value: String): Option[Position] = Some(apply(value))

  override def toString(value: Position): Option[String] = Some(value match {
    case Static => "static"
    case Relative => "relative"
    case Absolute => "absolute"
    case Fixed => "fixed"
    case Sticky => "sticky"
  })
}

sealed trait Position