package youi.component.types

import youi.Stringify

sealed abstract class FloatStyle(val value: String)

object FloatStyle extends Stringify[FloatStyle] {
  case object None extends FloatStyle("none")
  case object Left extends FloatStyle("left")
  case object Right extends FloatStyle("right")

  lazy val map: Map[String, FloatStyle] = List(None, Left, Right).map(f => f.value -> f).toMap

  override def fromString(value: String): Option[FloatStyle] = map.get(value.toLowerCase)

  override def toString(value: FloatStyle): Option[String] = if (value == None) {
    Option.empty[String]
  } else {
    Some(value.value)
  }
}