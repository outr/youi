package org.hyperscala.html.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class Draggable(val value: String) extends EnumEntryAttributeValue

object Draggable extends Enumerated[Draggable] with EnumEntryPersistence[Draggable] {
  case object True extends Draggable("true")
  case object False extends Draggable("false")
  case object Auto extends Draggable("auto")

  val values = findValues.toVector
}