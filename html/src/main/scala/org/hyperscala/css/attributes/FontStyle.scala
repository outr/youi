package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class FontStyle(val value: String) extends EnumEntryAttributeValue

object FontStyle extends Enumerated[FontStyle] with EnumEntryPersistence[FontStyle] {
  case object Normal extends FontStyle("normal")
  case object Italic extends FontStyle("italic")
  case object Oblique extends FontStyle("oblique")
  case object Inherit extends FontStyle("inherit")

  val values = findValues.toVector
}