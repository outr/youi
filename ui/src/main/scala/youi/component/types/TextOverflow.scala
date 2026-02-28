package youi.component.types

import youi.Stringify

sealed abstract class TextOverflow(val value: String)

object TextOverflow extends Stringify[TextOverflow] {
  case object Clip extends TextOverflow("clip")
  case object Ellipsis extends TextOverflow("ellipsis")

  lazy val map: Map[String, TextOverflow] = List(Clip, Ellipsis).map(f => f.value -> f).toMap

  override def fromString(value: String): Option[TextOverflow] = map.get(value.toLowerCase)

  override def toString(value: TextOverflow): Option[String] = if (value == Clip) {
    Option.empty[String]
  } else {
    Some(value.value)
  }
}