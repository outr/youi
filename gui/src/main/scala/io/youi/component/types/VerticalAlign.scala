package io.youi.component.types

import io.youi.Stringify

sealed abstract class VerticalAlign(val value: String)

object VerticalAlign extends Stringify[VerticalAlign] {
  case object Baseline extends VerticalAlign("baseline")
  case object Top extends VerticalAlign("top")
  case object Super extends VerticalAlign("super")
  case object Middle extends VerticalAlign("middle")
  case object Bottom extends VerticalAlign("bottom")
  case object Sub extends VerticalAlign("sub")
  case object TextTop extends VerticalAlign("text-top")
  case object TextBottom extends VerticalAlign("text-bottom")
  case object Unset extends VerticalAlign("unset")
  case class Number(pixels: Double) extends VerticalAlign(s"${pixels}px")

  lazy val map: Map[String, VerticalAlign] = List(Baseline, Top, Super, Middle, Bottom, Sub, TextTop, TextBottom, Unset)
    .map(va => va.value -> va)
    .toMap

  override def fromString(value: String): Option[VerticalAlign] = map.get(value.toLowerCase)

  override def toString(value: VerticalAlign): Option[String] = if (value == Unset) {
    None
  } else {
    Some(value.value)
  }
}