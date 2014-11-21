package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class FontWeight private(val value: String, val properName: String) extends EnumEntryAttributeValue

object FontWeight extends Enumerated[FontWeight] with EnumEntryPersistence[FontWeight] {
  val Normal = new FontWeight("normal", "Normal")
  val Bold = new FontWeight("bold", "Bold")
  val Lighter = new FontWeight("lighter", "Lighter")
  val Bolder = new FontWeight("bolder", "Bolder")
  val Weight100 = new FontWeight("100", "Ultra-Light")
  val Weight200 = new FontWeight("200", "Extra-Light")
  val Weight300 = new FontWeight("300", "Light")
  val Weight400 = new FontWeight("400", "Normal")
  val Weight500 = new FontWeight("500", "Medium")
  val Weight600 = new FontWeight("600", "Semi-Bold")
  val Weight700 = new FontWeight("700", "Bold")
  val Weight800 = new FontWeight("800", "Extra-Bold")
  val Weight000 = new FontWeight("900", "Ultra-Bold")
  val Inherit = new FontWeight("inherit", "Inherit")

  override def apply(name: String, caseSensitive: Boolean) = {
    get(name, caseSensitive).getOrElse(new FontWeight(name, name.capitalize))
  }
}