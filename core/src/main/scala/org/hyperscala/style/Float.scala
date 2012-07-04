package org.hyperscala.style

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
sealed case class Float(value: String) extends StyleValue

object Float {
  val Left = Float("left")
  val Right = Float("right")
  val None = Float("none")
  val Inherit = Float("inherit")
}
