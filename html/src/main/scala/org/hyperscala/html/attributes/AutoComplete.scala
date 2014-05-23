package org.hyperscala.html.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed class AutoComplete(val value: String) extends EnumEntryAttributeValue

object AutoComplete extends Enumerated[AutoComplete] with EnumEntryPersistence[AutoComplete] {
  val On = new AutoComplete("on")
  val Off = new AutoComplete("off")
}