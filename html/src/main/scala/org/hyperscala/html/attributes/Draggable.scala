package org.hyperscala.html.attributes

import org.powerscala.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
sealed class Draggable(val value: String) extends EnumEntry[Draggable] with AttributeValue

object Draggable extends Enumerated[Draggable] with EnumEntryPersistence[Draggable] {
  val True = new Draggable("true")
  val False = new Draggable("false")
  val Auto = new Draggable("auto")
}