package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.AttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Attachment private(val value: String) extends EnumEntry with AttributeValue

object Attachment extends Enumerated[Attachment] with EnumEntryPersistence[Attachment] {
  val Scroll = new Attachment("scroll")
  val Fixed = new Attachment("fixed")
  val Local = new Attachment("local")
  val Inherit = new Attachment("inherit")
}
