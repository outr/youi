package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class FontWeight(val value: String, val properName: String) extends EnumEntryAttributeValue

object FontWeight extends Enumerated[FontWeight] with EnumEntryPersistence[FontWeight] {
  case object Normal extends FontWeight("normal", "Normal")
  case object Bold extends FontWeight("bold", "Bold")
  case object Lighter extends FontWeight("lighter", "Lighter")
  case object Bolder extends FontWeight("bolder", "Bolder")
  case object Weight100 extends FontWeight("100", "Ultra-Light")
  case object Weight200 extends FontWeight("200", "Extra-Light")
  case object Weight300 extends FontWeight("300", "Light")
  case object Weight400 extends FontWeight("400", "Normal")
  case object Weight500 extends FontWeight("500", "Medium")
  case object Weight600 extends FontWeight("600", "Semi-Bold")
  case object Weight700 extends FontWeight("700", "Bold")
  case object Weight800 extends FontWeight("800", "Extra-Bold")
  case object Weight900 extends FontWeight("900", "Ultra-Bold")
  case object Inherit extends FontWeight("inherit", "Inherit")

  val values = findValues.toVector
}