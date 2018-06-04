package io.youi.style

import io.youi.theme.Stringify

object HTMLBorderStyle extends Stringify[HTMLBorderStyle] {
  case object None extends HTMLBorderStyle("none")
  case object Hidden extends HTMLBorderStyle("hidden")
  case object Dotted extends HTMLBorderStyle("dotted")
  case object Dashed extends HTMLBorderStyle("dashed")
  case object Solid extends HTMLBorderStyle("solid")
  case object Double extends HTMLBorderStyle("double")
  case object Groove extends HTMLBorderStyle("groove")
  case object Ridge extends HTMLBorderStyle("ridge")
  case object Inset extends HTMLBorderStyle("inset")
  case object Outset extends HTMLBorderStyle("outset")

  private lazy val map = List(None, Hidden, Dotted, Dashed, Solid, Double, Groove, Ridge, Inset, Outset)
    .map(s => s.value -> s)
    .toMap

  override def fromString(value: String): Option[HTMLBorderStyle] = map.get(value)

  override def toString(value: HTMLBorderStyle): Option[String] = Some(value.value)
}

sealed abstract class HTMLBorderStyle(val value: String)