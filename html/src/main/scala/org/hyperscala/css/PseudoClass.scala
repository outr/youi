package org.hyperscala.css

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class PseudoClass(value: String) extends EnumEntry

object PseudoClass extends Enumerated[PseudoClass] {
  val firstChild = PseudoClass("first-child")
  val link = PseudoClass("link")
  val visited = PseudoClass("visited")
  val active = PseudoClass("active")
  val hover = PseudoClass("hover")
  val focus = PseudoClass("focus")
  def lang(language: String) = PseudoClass(s"lang($language)")
}