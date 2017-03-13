package io.youi.hypertext.border

sealed abstract class BorderStyle(val value: String)

object BorderStyle {
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
}