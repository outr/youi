package org.hyperscala.html.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class DropZone(val value: String) extends EnumEntryAttributeValue

object DropZone extends Enumerated[DropZone] with EnumEntryPersistence[DropZone] {
  case object Copy extends DropZone("copy")
  case object Move extends DropZone("move")
  case object Link extends DropZone("link")

  val values = findValues.toVector
}