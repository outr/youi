package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class Overflow(val value: String) extends EnumEntryAttributeValue

object Overflow extends Enumerated[Overflow] with EnumEntryPersistence[Overflow] {
  case object Auto extends Overflow("auto")
  case object Hidden extends Overflow("hidden")
  case object Visible extends Overflow("visible")
  case object Scroll extends Overflow("scroll")
  case object Inherit extends Overflow("inherit")
  case object NoDisplay extends Overflow("no-display")
  case object NoContent extends Overflow("no-content")

  val values = findValues.toVector
}