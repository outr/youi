package org.hyperscala.html.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class ButtonType(val value: String) extends EnumEntryAttributeValue

object ButtonType extends Enumerated[ButtonType] with EnumEntryPersistence[ButtonType] {
  case object Button extends ButtonType("button")
  case object Submit extends ButtonType("submit")
  case object Reset extends ButtonType("reset")

  val values = findValues.toVector
}