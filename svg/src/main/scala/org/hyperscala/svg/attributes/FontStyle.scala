package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed abstract class FontStyle extends AttributeEntry[FontStyle](parent = FontStyle)

object FontStyle extends AttributeObject[FontStyle] {
  case object Normal extends FontStyle
  case object Italic extends FontStyle
  case object Oblique extends FontStyle
  case object Inherit extends FontStyle

  val values = findValues.toVector
}