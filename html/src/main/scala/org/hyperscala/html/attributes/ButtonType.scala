package org.hyperscala.html.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed class ButtonType(val value: String) extends EnumEntryAttributeValue

object ButtonType extends Enumerated[ButtonType] with EnumEntryPersistence[ButtonType] {
  val Button = new ButtonType("button")
  val Submit = new ButtonType("submit")
  val Reset = new ButtonType("reset")
}