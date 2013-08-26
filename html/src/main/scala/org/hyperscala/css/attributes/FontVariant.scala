package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.AttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class FontVariant private(val value: String) extends EnumEntry with AttributeValue

object FontVariant extends Enumerated[FontVariant] with EnumEntryPersistence[FontVariant] {
  val Normal = new FontVariant("normal")
  val SmallCaps = new FontVariant("small-caps")
  val Inherit = new FontVariant("inherit")
}
