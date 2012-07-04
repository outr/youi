package org.hyperscala.style

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
sealed case class WhiteSpace(value: String) extends StyleValue

object WhiteSpace {
  val Normal = WhiteSpace("normal")
  val NoWrap = WhiteSpace("nowrap")
  val Pre = WhiteSpace("pre")
  val PreLine = WhiteSpace("pre-line")
  val PreWrap = WhiteSpace("pre-wrap")
  val Inherit = WhiteSpace("inherit")
}