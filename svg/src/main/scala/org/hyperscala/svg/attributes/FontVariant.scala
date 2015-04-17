package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed abstract class FontVariant extends AttributeEntry[FontVariant](parent = FontVariant)

object FontVariant extends AttributeObject[FontVariant] {
  case object Normal extends FontVariant
  case object SmallCaps extends FontVariant
  case object Inherit extends FontVariant

  val values = findValues.toVector
}