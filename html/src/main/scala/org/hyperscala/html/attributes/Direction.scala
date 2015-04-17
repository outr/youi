package org.hyperscala.html.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class Direction(val value: String) extends EnumEntryAttributeValue

object Direction extends Enumerated[Direction] with EnumEntryPersistence[Direction] {
  case object LeftToRight extends Direction("ltr")
  case object RightToLeft extends Direction("rtl")
  case object Auto extends Direction("auto")

  val values = findValues.toVector
}