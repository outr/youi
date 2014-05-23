package org.hyperscala.html.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed class TextAreaWrap(val value: String) extends EnumEntryAttributeValue

object TextAreaWrap extends Enumerated[TextAreaWrap] with EnumEntryPersistence[TextAreaWrap] {
  val Hard = new TextAreaWrap("hard")
  val Soft = new TextAreaWrap("soft")
}