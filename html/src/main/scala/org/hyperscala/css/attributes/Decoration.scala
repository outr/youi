package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class Decoration(val value: String) extends EnumEntryAttributeValue

object Decoration extends Enumerated[Decoration] with EnumEntryPersistence[Decoration] {
  case object None extends Decoration("none")
  case object Underline extends Decoration("underline")
  case object Overline extends Decoration("overline")
  case object LineThrough extends Decoration("line-through")

  val values = findValues.toVector
}
