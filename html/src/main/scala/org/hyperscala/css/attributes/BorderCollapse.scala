package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.AttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class BorderCollapse private(val value: String) extends EnumEntry with AttributeValue

object BorderCollapse extends Enumerated[BorderCollapse] with EnumEntryPersistence[BorderCollapse] {
  val Separate = new BorderCollapse("separate")
  val Collapse = new BorderCollapse("Collapse")
  val Inherit = new BorderCollapse("inherit")
}