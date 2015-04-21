package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class Repeat(val value: String) extends EnumEntryAttributeValue

object Repeat extends Enumerated[Repeat] with EnumEntryPersistence[Repeat] {
  case object Repeat extends Repeat("repeat")
  case object Space extends Repeat("space")
  case object Round extends Repeat("round")
  case object NoRepeat extends Repeat("no-repeat")
  case object Inherit extends Repeat("inherit")

  val values = findValues.toVector
}