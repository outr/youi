package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class FontStyle private(val value: String) extends EnumEntryAttributeValue

object FontStyle extends Enumerated[FontStyle] with EnumEntryPersistence[FontStyle] {
  val Normal = new FontStyle("normal")
  val Italic = new FontStyle("italic")
  val Oblique = new FontStyle("oblique")
  val Inherit = new FontStyle("inherit")

  override def apply(name: String, caseSensitive: Boolean) = {
    get(name, caseSensitive).getOrElse(new FontStyle(name))
  }
}