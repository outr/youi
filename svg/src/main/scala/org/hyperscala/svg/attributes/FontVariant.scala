package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed class FontVariant extends AttributeEntry[FontVariant]

object FontVariant extends AttributeObject[FontVariant] {
  val Normal = new FontVariant
  val SmallCaps = new FontVariant
  val Inherit = new FontVariant
}