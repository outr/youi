package org.hyperscala.html.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
sealed class TextAreaWrap(val value: String) extends EnumEntry with AttributeValue

object TextAreaWrap extends Enumerated[TextAreaWrap] with EnumEntryPersistence[TextAreaWrap] {
  val Hard = new TextAreaWrap("hard")
  val Soft = new TextAreaWrap("soft")
}