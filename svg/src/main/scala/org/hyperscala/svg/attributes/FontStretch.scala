package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed abstract class FontStretch extends AttributeEntry[FontStretch](parent = FontStretch)

object FontStretch extends AttributeObject[FontStretch] {
  case object Normal extends FontStretch
  case object Wider extends FontStretch
  case object Narrower extends FontStretch
  case object UltraCondensed extends FontStretch
  case object ExtraCondensed extends FontStretch
  case object Condensed extends FontStretch
  case object SemiCondensed extends FontStretch
  case object SemiExpanded extends FontStretch
  case object Expanded extends FontStretch
  case object ExtraExpanded extends FontStretch
  case object UltraExpanded extends FontStretch
  case object Inherit extends FontStretch

  val values = findValues.toVector
}