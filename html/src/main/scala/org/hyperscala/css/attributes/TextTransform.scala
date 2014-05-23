package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class TextTransform private(val value: String) extends EnumEntryAttributeValue

object TextTransform extends Enumerated[TextTransform] with EnumEntryPersistence[TextTransform] {
  val None = new TextTransform("none")
  val Capitalize = new TextTransform("capitalize")
  val Uppercase = new TextTransform("uppercase")
  val Lowercase = new TextTransform("lowercase")
  val FullWidth = new TextTransform("full-width")
  val Inherit = new TextTransform("inherit")
}
