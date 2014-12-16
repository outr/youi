package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ListStyleType private(val value: String, val description: String, val ordered: Boolean = false, val unordered: Boolean = false) extends EnumEntryAttributeValue

object ListStyleType extends Enumerated[ListStyleType] with EnumEntryPersistence[ListStyleType] {
  val None = new ListStyleType("none", "No marker is shown")
  val Initial = new ListStyleType("initial", "Restores default value")
  val Inherit = new ListStyleType("inherit", "Inherits from its parent")
  val Disc = new ListStyleType("disc", "Marker is a filled circle", unordered = true)
  val Circle = new ListStyleType("circle", "Marker is a circle", unordered = true)
  val Square = new ListStyleType("square", "Marker is a square", unordered = true)
  val Decimal = new ListStyleType("decimal", "Numeric (1, 2, 3, ...)", ordered = true)
  val LowerAlpha = new ListStyleType("lower-alpha", "Lowercase Alphabet (a, b, c, ...)", ordered = true)
  val UpperAlpha = new ListStyleType("upper-alpha", "Uppercase Alphabet (A, B, C, ...)", ordered = true)
  val LowerRoman = new ListStyleType("lower-roman", "Lowercase Roman (i, ii, iii, ...)", ordered = true)
  val UpperRoman = new ListStyleType("upper-roman", "Uppercase Roman (I, II, III, ...)", ordered = true)
  val LowerLatin = new ListStyleType("lower-latin", "Lowercase Latin (a, b, c, ...)", ordered = true)
  val UpperLatin = new ListStyleType("upper-latin", "Uppercase Latin (A, B, C, ...)", ordered = true)
  val LowerGreek = new ListStyleType("lower-greek", "Lowercase Greek Marker", ordered = true)
  val Armenian = new ListStyleType("armenian", "Armenian Numbering", ordered = true)
  val Georgian = new ListStyleType("georgian", "Georgian Numbering", ordered = true)
  val DecimalLeadingZero = new ListStyleType("decimal-leading-zero", "Numeric Leading Zero (01, 02, 03, ...)", ordered = true)
}