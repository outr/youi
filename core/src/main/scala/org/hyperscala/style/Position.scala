package com.outr.webframework.style

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
sealed case class Position(value: String) extends StyleValue

object Position {
  val Static = Position("static")
  val Absolute = Position("absolute")
  val Fixed = Position("fixed")
  val Relative = Position("relative")
  val Inherit = Position("inherit")
}
