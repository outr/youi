package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class Origin(val value: String) extends EnumEntryAttributeValue

object Origin extends Enumerated[Origin] with EnumEntryPersistence[Origin] {
  case object BorderBox extends Origin("border-box")
  case object PaddingBox extends Origin("padding-box")
  case object ContentBox extends Origin("content-box")
  case object Inherit extends Origin("inherit")

  val values = findValues.toVector
}