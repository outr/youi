package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed class Float(val value: String) extends EnumEntryAttributeValue

object Float extends Enumerated[Float] with EnumEntryPersistence[Float] {
  val Left = new Float("left")
  val Right = new Float("right")
  val None = new Float("none")
  val Inherit = new Float("inherit")
}