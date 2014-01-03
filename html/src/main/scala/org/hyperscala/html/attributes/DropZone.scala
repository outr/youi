package org.hyperscala.html.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed class DropZone(val value: String) extends EnumEntry with AttributeValue

object DropZone extends Enumerated[DropZone] with EnumEntryPersistence[DropZone] {
  val Copy = new DropZone("copy")
  val Move = new DropZone("move")
  val Link = new DropZone("link")
}