package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class Clear(val value: String) extends EnumEntryAttributeValue

object Clear extends Enumerated[Clear] with EnumEntryPersistence[Clear] {
  case object Both extends Clear("both")
  case object Inherit extends Clear("inherit")
  case object Left extends Clear("left")
  case object None extends Clear("none")
  case object Right extends Clear("right")

  val values = findValues.toVector
}