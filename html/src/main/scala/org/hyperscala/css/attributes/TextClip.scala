package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class TextClip(val value: String) extends EnumEntryAttributeValue

object TextClip extends Enumerated[TextClip] with EnumEntryPersistence[TextClip] {
  case object Clip extends TextClip("clip")
  case object Ellipsis extends TextClip("ellipsis")
  case object Inherit extends TextClip("inherit")

  val values = findValues.toVector
}