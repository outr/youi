package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class Float(val value: String) extends EnumEntryAttributeValue

object Float extends Enumerated[Float] with EnumEntryPersistence[Float] {
  case object Left extends Float("left")
  case object Right extends Float("right")
  case object None extends Float("none")
  case object Inherit extends Float("inherit")

  val values = findValues.toVector
}