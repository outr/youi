package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed class Clear(val value: String) extends EnumEntryAttributeValue

object Clear extends Enumerated[Clear] with EnumEntryPersistence[Clear] {
  val Both = new Clear("both")
  val Inherit = new Clear("inherit")
  val Left = new Clear("left")
  val None = new Clear("none")
  val Right = new Clear("right")
}