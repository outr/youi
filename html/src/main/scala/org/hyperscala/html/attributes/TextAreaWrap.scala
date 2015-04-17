package org.hyperscala.html.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class TextAreaWrap(val value: String) extends EnumEntryAttributeValue

object TextAreaWrap extends Enumerated[TextAreaWrap] with EnumEntryPersistence[TextAreaWrap] {
  case object Hard extends TextAreaWrap("hard")
  case object Soft extends TextAreaWrap("soft")

  val values = findValues.toVector
}