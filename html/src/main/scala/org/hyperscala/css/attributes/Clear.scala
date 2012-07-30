package org.hyperscala.css.attributes

import org.powerscala.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
sealed class Clear(val value: String) extends EnumEntry[Clear] with AttributeValue

object Clear extends Enumerated[Clear] with EnumEntryPersistence[Clear] {
  val Both = new Clear("both")
  val Inherit = new Clear("inherit")
  val Left = new Clear("left")
  val None = new Clear("none")
  val Right = new Clear("right")
}