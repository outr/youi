package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed class FontStyle extends AttributeEntry[FontStyle]

object FontStyle extends AttributeObject[FontStyle] {
  val Normal = new FontStyle
  val Italic = new FontStyle
  val Oblique = new FontStyle
  val Inherit = new FontStyle
}