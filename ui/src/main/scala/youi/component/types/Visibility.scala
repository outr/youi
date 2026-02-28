package youi.component.types

import youi.Stringify

sealed abstract class Visibility(val value: String)

object Visibility extends Stringify[Visibility] {
  case object Unset extends Visibility("")
  case object Visible extends Visibility("visible")
  case object Hidden extends Visibility("hidden")
  case object Collapse extends Visibility("collapse")

  lazy val map: Map[String, Visibility] = List(Unset, Visible, Hidden, Collapse).map(v => v.value -> v).toMap

  override def fromString(value: String): Option[Visibility] = map.get(value)

  override def toString(value: Visibility): Option[String] = if (value == Unset) {
    None
  } else {
    Some(value.value)
  }
}