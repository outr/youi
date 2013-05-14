package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
sealed class Overflow(val value: String) extends EnumEntry with AttributeValue

object Overflow extends Enumerated[Overflow] with EnumEntryPersistence[Overflow] {
  val Auto = new Overflow("auto")
  val Hidden = new Overflow("hidden")
  val Visible = new Overflow("visible")
  val Scroll = new Overflow("scroll")
  val Inherit = new Overflow("inherit")
  val NoDisplay = new Overflow("no-display")
  val NoContent = new Overflow("no-content")
}