package org.hyperscala.html.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
sealed class ContentEditable(val value: String) extends EnumEntry with AttributeValue

object ContentEditable extends Enumerated[ContentEditable] with EnumEntryPersistence[ContentEditable] {
  val True = new ContentEditable("true")
  val False = new ContentEditable("false")
  val Inherit = new ContentEditable("inherit")
}