package org.hyperscala.style

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
sealed case class Clear(value: String) extends StyleValue

object Clear {
  val Left = Clear("left")
  val Right = Clear("right")
  val Both = Clear("both")
  val None = Clear("none")
  val Inherit = Clear("inherit")
}
