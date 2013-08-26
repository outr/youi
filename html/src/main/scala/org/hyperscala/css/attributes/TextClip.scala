package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.AttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class TextClip private(val value: String) extends EnumEntry with AttributeValue

object TextClip extends Enumerated[TextClip] with EnumEntryPersistence[TextClip] {
  val Clip = new TextClip("clip")
  val Ellipsis = new TextClip("ellipsis")
  val Inherit = new TextClip("inherit")
}