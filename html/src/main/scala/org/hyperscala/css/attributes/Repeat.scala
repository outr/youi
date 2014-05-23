package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Repeat private(val value: String) extends EnumEntryAttributeValue

object Repeat extends Enumerated[Repeat] with EnumEntryPersistence[Repeat] {
  val Repeat = new Repeat("repeat")
  val Space = new Repeat("space")
  val Round = new Repeat("round")
  val NoRepeat = new Repeat("no-repeat")
  val Inherit = new Repeat("inherit")
}