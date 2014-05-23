package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class TextClip private(val value: String) extends EnumEntryAttributeValue

object TextClip extends Enumerated[TextClip] with EnumEntryPersistence[TextClip] {
  val Clip = new TextClip("clip")
  val Ellipsis = new TextClip("ellipsis")
  val Inherit = new TextClip("inherit")
}