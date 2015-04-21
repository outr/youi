package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class Visibility private(val value: String) extends EnumEntryAttributeValue

object Visibility extends Enumerated[Visibility] with EnumEntryPersistence[Visibility] {
  case object Visible extends Visibility("visible")
  case object Hidden extends Visibility("hidden")
  case object Collapse extends Visibility("collapse")
  case object Inherit extends Visibility("inherit")

  val values = findValues.toVector
}
