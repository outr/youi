package io.youi.component.types

import io.youi.Stringify

object TextAlign extends Stringify[TextAlign] {
  case object Left extends TextAlign("left")
  case object Right extends TextAlign("right")
  case object Center extends TextAlign("center")
  case object Justify extends TextAlign("justify")
  case object JustifyAll extends TextAlign("justify-all")
  case object Start extends TextAlign("start")
  case object End extends TextAlign("end")
  case object MatchParent extends TextAlign("match-parent")
  case object Inherit extends TextAlign("inherit")
  case object Initial extends TextAlign("initial")
  case object Unset extends TextAlign("unset")

  lazy val map: Map[String, TextAlign] = {
    List(Left, Right, Center, Justify, JustifyAll, Start, End, MatchParent, Inherit, Initial, Unset)
      .map(ta => ta.value -> ta).toMap
  }

  override def fromString(value: String): Option[TextAlign] = map.get(value.toLowerCase)

  override def toString(value: TextAlign): Option[String] = Some(value.value)
}

sealed abstract class TextAlign(val value: String)