package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.AttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Visibility private(val value: String) extends EnumEntry with AttributeValue

object Visibility extends Enumerated[Visibility] with EnumEntryPersistence[Visibility] {
  val Visible = new Visibility("visible")
  val Hidden = new Visibility("hidden")
  val Collapse = new Visibility("collapse")
  val Inherit = new Visibility("inherit")
}
