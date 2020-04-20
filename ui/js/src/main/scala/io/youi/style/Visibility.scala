package io.youi.style

sealed abstract class Visibility(val value: String)

object Visibility extends Stringify[Visibility] {
  case object Visible extends Visibility("visible")
  case object Hidden extends Visibility("hidden")
  case object Collapse extends Visibility("collapse")

  lazy val map: Map[String, Visibility] = List(Visible, Hidden, Collapse).map(v => v.value -> v).toMap

  override def fromString(value: String): Option[Visibility] = map.get(value)

  override def toString(value: Visibility): Option[String] = if (value == Visible) {
    None
  } else {
    Some(value.value)
  }
}