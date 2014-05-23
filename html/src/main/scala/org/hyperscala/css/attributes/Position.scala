package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
class Position private(val value: String) extends EnumEntryAttributeValue

object Position extends Enumerated[Position] with EnumEntryPersistence[Position] {
  val Static = new Position("static")
  val Inherit = new Position("inherit")
  val Absolute = new Position("absolute")
  val Fixed = new Position("fixed")
  val Relative = new Position("relative")
}