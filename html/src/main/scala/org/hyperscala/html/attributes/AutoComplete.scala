package org.hyperscala.html.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class AutoComplete(val value: String) extends EnumEntryAttributeValue

object AutoComplete extends Enumerated[AutoComplete] with EnumEntryPersistence[AutoComplete] {
  case object On extends AutoComplete("on")
  case object Off extends AutoComplete("off")

  val values = findValues.toVector
}