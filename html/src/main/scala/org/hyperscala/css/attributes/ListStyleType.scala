package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class ListStyleType(val value: String, val description: String, val ordered: Boolean = false, val unordered: Boolean = false) extends EnumEntryAttributeValue

object ListStyleType extends Enumerated[ListStyleType] with EnumEntryPersistence[ListStyleType] {
  case object None extends ListStyleType("none", "No marker is shown")
  case object Initial extends ListStyleType("initial", "Restores default value")
  case object Inherit extends ListStyleType("inherit", "Inherits from its parent")
  case object Disc extends ListStyleType("disc", "Marker is a filled circle", unordered = true)
  case object Circle extends ListStyleType("circle", "Marker is a circle", unordered = true)
  case object Square extends ListStyleType("square", "Marker is a square", unordered = true)
  case object Decimal extends ListStyleType("decimal", "Numeric (1, 2, 3, ...)", ordered = true)
  case object LowerAlpha extends ListStyleType("lower-alpha", "Lowercase Alphabet (a, b, c, ...)", ordered = true)
  case object UpperAlpha extends ListStyleType("upper-alpha", "Uppercase Alphabet (A, B, C, ...)", ordered = true)
  case object LowerRoman extends ListStyleType("lower-roman", "Lowercase Roman (i, ii, iii, ...)", ordered = true)
  case object UpperRoman extends ListStyleType("upper-roman", "Uppercase Roman (I, II, III, ...)", ordered = true)
  case object LowerLatin extends ListStyleType("lower-latin", "Lowercase Latin (a, b, c, ...)", ordered = true)
  case object UpperLatin extends ListStyleType("upper-latin", "Uppercase Latin (A, B, C, ...)", ordered = true)
  case object LowerGreek extends ListStyleType("lower-greek", "Lowercase Greek Marker", ordered = true)
  case object Armenian extends ListStyleType("armenian", "Armenian Numbering", ordered = true)
  case object Georgian extends ListStyleType("georgian", "Georgian Numbering", ordered = true)
  case object DecimalLeadingZero extends ListStyleType("decimal-leading-zero", "Numeric Leading Zero (01, 02, 03, ...)", ordered = true)

  val values = findValues.toVector
}