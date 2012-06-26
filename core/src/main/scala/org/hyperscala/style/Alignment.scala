package com.outr.webframework.style

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
sealed case class Alignment(value: String) extends StyleValue

object Alignment {
  val Left = Alignment("left")
  val Right = Alignment("right")
  val Center = Alignment("center")
  val Justify = Alignment("justify")
  val Inherit = Alignment("inherit")
}