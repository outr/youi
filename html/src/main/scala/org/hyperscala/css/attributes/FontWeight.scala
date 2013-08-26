package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.AttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class FontWeight private(val value: String) extends EnumEntry with AttributeValue

object FontWeight extends Enumerated[FontWeight] with EnumEntryPersistence[FontWeight] {
  val Normal = new FontWeight("normal")
  val Bold = new FontWeight("bold")
  val Lighter = new FontWeight("lighter")
  val Bolder = new FontWeight("bolder")
  val Weight100 = new FontWeight("100")
  val Weight200 = new FontWeight("200")
  val Weight300 = new FontWeight("300")
  val Weight400 = new FontWeight("400")
  val Weight500 = new FontWeight("500")
  val Weight600 = new FontWeight("600")
  val Weight700 = new FontWeight("700")
  val Weight800 = new FontWeight("800")
  val Weight000 = new FontWeight("900")
  val Inherit = new FontWeight("inherit")
}