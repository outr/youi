package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
sealed class Float(val value: String) extends EnumEntry with AttributeValue

object Float extends Enumerated[Float] with EnumEntryPersistence[Float] {
  val Left = new Float("left")
  val Right = new Float("right")
  val None = new Float("none")
  val Inherit = new Float("inherit")
}