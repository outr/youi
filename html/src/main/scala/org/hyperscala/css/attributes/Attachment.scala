package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class Attachment private(val value: String) extends EnumEntryAttributeValue

object Attachment extends Enumerated[Attachment] with EnumEntryPersistence[Attachment] {
  case object Scroll extends Attachment("scroll")
  case object Fixed extends Attachment("fixed")
  case object Local extends Attachment("local")
  case object Inherit extends Attachment("inherit")

  val values = findValues.toVector
}
