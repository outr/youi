package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed class Overflow(val value: String) extends EnumEntryAttributeValue

object Overflow extends Enumerated[Overflow] with EnumEntryPersistence[Overflow] {
  val Auto = new Overflow("auto")
  val Hidden = new Overflow("hidden")
  val Visible = new Overflow("visible")
  val Scroll = new Overflow("scroll")
  val Inherit = new Overflow("inherit")
  val NoDisplay = new Overflow("no-display")
  val NoContent = new Overflow("no-content")
}