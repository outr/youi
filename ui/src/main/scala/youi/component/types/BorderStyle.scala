package youi.component.types

import youi.Stringify

object BorderStyle extends Stringify[BorderStyle] {
  case object None extends BorderStyle("none")
  case object Hidden extends BorderStyle("hidden")
  case object Dotted extends BorderStyle("dotted")
  case object Dashed extends BorderStyle("dashed")
  case object Solid extends BorderStyle("solid")
  case object Double extends BorderStyle("double")
  case object Groove extends BorderStyle("groove")
  case object Ridge extends BorderStyle("ridge")
  case object Inset extends BorderStyle("inset")
  case object Outset extends BorderStyle("outset")

  private lazy val map = List(None, Hidden, Dotted, Dashed, Solid, Double, Groove, Ridge, Inset, Outset)
    .map(s => s.value -> s)
    .toMap

  override def fromString(value: String): Option[BorderStyle] = map.get(value)

  override def toString(value: BorderStyle): Option[String] = Some(value.value)
}

sealed abstract class BorderStyle(val value: String)