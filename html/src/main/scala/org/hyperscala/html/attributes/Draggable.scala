package org.hyperscala.html.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed class Draggable(val value: String) extends EnumEntryAttributeValue

object Draggable extends Enumerated[Draggable] with EnumEntryPersistence[Draggable] {
  val True = new Draggable("true")
  val False = new Draggable("false")
  val Auto = new Draggable("auto")
}