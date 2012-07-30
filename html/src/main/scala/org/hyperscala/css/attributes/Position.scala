package org.hyperscala.css.attributes

import org.powerscala.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
sealed class Position(val value: String) extends EnumEntry[Position] with AttributeValue

object Position extends Enumerated[Position] with EnumEntryPersistence[Position] {
  val Static = new Position("static")
  val Inherit = new Position("inherit")
  val Absolute = new Position("absolute")
  val Fixed = new Position("fixed")
  val Relative = new Position("relative")
}