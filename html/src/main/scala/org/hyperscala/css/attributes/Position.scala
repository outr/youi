package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class Position(val value: String) extends EnumEntryAttributeValue

object Position extends Enumerated[Position] with EnumEntryPersistence[Position] {
  case object Static extends Position("static")
  case object Inherit extends Position("inherit")
  case object Absolute extends Position("absolute")
  case object Fixed extends Position("fixed")
  case object Relative extends Position("relative")

  val values = findValues.toVector
}