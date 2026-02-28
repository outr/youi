package youi.component.types

sealed trait DropType

object DropType {
  case object Auto extends DropType
  case object Down extends DropType
  case object Up extends DropType
}