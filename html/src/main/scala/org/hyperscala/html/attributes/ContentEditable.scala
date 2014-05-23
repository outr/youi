package org.hyperscala.html.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed class ContentEditable(val value: String) extends EnumEntryAttributeValue

object ContentEditable extends Enumerated[ContentEditable] with EnumEntryPersistence[ContentEditable] {
  val True = new ContentEditable("true")
  val False = new ContentEditable("false")
  val Inherit = new ContentEditable("inherit")
}