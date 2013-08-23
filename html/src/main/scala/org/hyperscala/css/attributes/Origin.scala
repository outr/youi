package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.AttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Origin private(val value: String) extends EnumEntry with AttributeValue

object Origin extends Enumerated[Origin] with EnumEntryPersistence[Origin] {
  val BorderBox = new Origin("border-box")
  val PaddingBox = new Origin("padding-box")
  val ContentBox = new Origin("content-box")
  val Inherit = new Origin("inherit")
}