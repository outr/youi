package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Visibility private(val value: String) extends EnumEntryAttributeValue

object Visibility extends Enumerated[Visibility] with EnumEntryPersistence[Visibility] {
  val Visible = new Visibility("visible")
  val Hidden = new Visibility("hidden")
  val Collapse = new Visibility("collapse")
  val Inherit = new Visibility("inherit")
}
