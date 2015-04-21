package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class Alignment(val value: String) extends EnumEntryAttributeValue

object Alignment extends Enumerated[Alignment] with EnumEntryPersistence[Alignment] {
  case object Justify extends Alignment("justify")
  case object Center extends Alignment("center")
  case object Inherit extends Alignment("inherit")
  case object Left extends Alignment("left")
  case object Right extends Alignment("right")

  val values = findValues.toVector
}