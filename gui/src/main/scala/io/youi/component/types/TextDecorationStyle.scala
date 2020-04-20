package io.youi.component.types

import io.youi.Stringify

object TextDecorationStyle extends Stringify[TextDecorationStyle] {
  case object Solid extends TextDecorationStyle("solid")
  case object Double extends TextDecorationStyle("double")
  case object Dotted extends TextDecorationStyle("dotted")
  case object Dashed extends TextDecorationStyle("dashed")
  case object Wavy extends TextDecorationStyle("wavy")
  case object Inherit extends TextDecorationStyle("inherit")
  case object Initial extends TextDecorationStyle("initial")
  case object Unset extends TextDecorationStyle("unset")

  lazy val map: Map[String, TextDecorationStyle] = {
    List(Solid, Double, Dotted, Dashed, Wavy, Inherit, Initial, Unset).map(tds => tds.value -> tds).toMap
  }

  override def fromString(value: String): Option[TextDecorationStyle] = map.get(value.toLowerCase)

  override def toString(value: TextDecorationStyle): Option[String] = Some(value.value)
}

sealed abstract class TextDecorationStyle(val value: String)