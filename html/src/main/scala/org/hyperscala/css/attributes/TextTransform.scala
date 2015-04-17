package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class TextTransform(val value: String) extends EnumEntryAttributeValue

object TextTransform extends Enumerated[TextTransform] with EnumEntryPersistence[TextTransform] {
  case object None extends TextTransform("none")
  case object Capitalize extends TextTransform("capitalize")
  case object Uppercase extends TextTransform("uppercase")
  case object Lowercase extends TextTransform("lowercase")
  case object FullWidth extends TextTransform("full-width")
  case object Inherit extends TextTransform("inherit")

  val values = findValues.toVector
}
