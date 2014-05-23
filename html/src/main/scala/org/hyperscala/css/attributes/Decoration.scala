package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Decoration private(val value: String) extends EnumEntryAttributeValue

object Decoration extends Enumerated[Decoration] with EnumEntryPersistence[Decoration] {
  val None = new Decoration("none")
  val Underline = new Decoration("underline")
  val Overline = new Decoration("overline")
  val LineThrough = new Decoration("line-through")
}
