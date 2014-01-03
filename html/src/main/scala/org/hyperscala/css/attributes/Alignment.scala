package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed class Alignment(val value: String) extends EnumEntry with AttributeValue

object Alignment extends Enumerated[Alignment] with EnumEntryPersistence[Alignment] {
  val Justify = new Alignment("justify")
  val Center = new Alignment("center")
  val Inherit = new Alignment("inherit")
  val Left = new Alignment("left")
  val Right = new Alignment("right")
}