package org.hyperscala.html.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class ContentEditable(val value: String) extends EnumEntryAttributeValue

object ContentEditable extends Enumerated[ContentEditable] with EnumEntryPersistence[ContentEditable] {
  case object True extends ContentEditable("true")
  case object False extends ContentEditable("false")
  case object Inherit extends ContentEditable("inherit")

  val values = findValues.toVector
}