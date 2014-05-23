package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class FontVariant private(val value: String) extends EnumEntryAttributeValue

object FontVariant extends Enumerated[FontVariant] with EnumEntryPersistence[FontVariant] {
  val Normal = new FontVariant("normal")
  val SmallCaps = new FontVariant("small-caps")
  val Inherit = new FontVariant("inherit")
}
