package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed class Visibility extends AttributeEntry[Visibility](parent = Visibility)

object Visibility extends AttributeObject[Visibility] {
  val Visible = new Visibility
  val Hidden = new Visibility
  val Inherit = new Visibility
}
