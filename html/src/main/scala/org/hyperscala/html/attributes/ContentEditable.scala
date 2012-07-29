package org.hyperscala.html.attributes

import org.powerscala.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
sealed class ContentEditable(val value: String) extends EnumEntry[ContentEditable] with AttributeValue

object ContentEditable extends Enumerated[ContentEditable] with EnumEntryPersistence[ContentEditable] {
  val True = new ContentEditable("true")
  val False = new ContentEditable("false")
  val Inherit = new ContentEditable("inherit")
}