package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class FontVariant(val value: String) extends EnumEntryAttributeValue

object FontVariant extends Enumerated[FontVariant] with EnumEntryPersistence[FontVariant] {
  case object Normal extends FontVariant("normal")
  case object SmallCaps extends FontVariant("small-caps")
  case object Inherit extends FontVariant("inherit")

  val values = findValues.toVector
}
