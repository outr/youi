package org.hyperscala.svg.attributes

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed class FontStretch extends AttributeEntry[FontStretch]

object FontStretch extends AttributeObject[FontStretch] {
  val Normal = new FontStretch
  val Wider = new FontStretch
  val Narrower = new FontStretch
  val UltraCondensed = new FontStretch
  val ExtraCondensed = new FontStretch
  val Condensed = new FontStretch
  val SemiCondensed = new FontStretch
  val SemiExpanded = new FontStretch
  val Expanded = new FontStretch
  val ExtraExpanded = new FontStretch
  val UltraExpanded = new FontStretch
  val Inherit = new FontStretch
}