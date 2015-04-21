package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class Visibility extends AttributeEntry[Visibility](parent = Visibility)

object Visibility extends AttributeObject[Visibility] {
  case object Visible extends Visibility
  case object Hidden extends Visibility
  case object Inherit extends Visibility

  val values = findValues.toVector
}
