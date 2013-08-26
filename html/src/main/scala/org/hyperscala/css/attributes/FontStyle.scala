package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.AttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class FontStyle private(val value: String) extends EnumEntry with AttributeValue

object FontStyle extends Enumerated[FontStyle] with EnumEntryPersistence[FontStyle] {
  val Normal = new FontStyle("normal")
  val Italic = new FontStyle("italic")
  val Oblique = new FontStyle("oblique")
  val Inherit = new FontStyle("inherit")
}