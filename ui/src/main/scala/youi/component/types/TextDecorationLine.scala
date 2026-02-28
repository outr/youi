package youi.component.types

import youi.Stringify

object TextDecorationLine extends Stringify[TextDecorationLine] {
  case object None extends TextDecorationLine("none")
  case object Underline extends TextDecorationLine("underline")
  case object Overline extends TextDecorationLine("overline")
  case object LineThrough extends TextDecorationLine("line-through")

  lazy val map: Map[String, TextDecorationLine] = {
    List(None, Underline, Overline, LineThrough).map(tdl => tdl.value -> tdl).toMap
  }

  override def fromString(value: String): Option[TextDecorationLine] = map.get(value.toLowerCase)

  override def toString(value: TextDecorationLine): Option[String] = Some(value.value)
}

sealed abstract class TextDecorationLine(val value: String)