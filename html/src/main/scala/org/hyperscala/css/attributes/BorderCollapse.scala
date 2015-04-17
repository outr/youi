package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class BorderCollapse(val value: String) extends EnumEntryAttributeValue

object BorderCollapse extends Enumerated[BorderCollapse] with EnumEntryPersistence[BorderCollapse] {
  case object Separate extends BorderCollapse("separate")
  case object Collapse extends BorderCollapse("Collapse")
  case object Inherit extends BorderCollapse("inherit")

  val values = findValues.toVector
}