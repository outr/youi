package org.hyperscala.html.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
sealed class ButtonType(val value: String) extends EnumEntry with AttributeValue

object ButtonType extends Enumerated[ButtonType] with EnumEntryPersistence[ButtonType] {
  val Button = new ButtonType("button")
  val Submit = new ButtonType("submit")
  val Reset = new ButtonType("reset")
}