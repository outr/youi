package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.AttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Decoration private(val value: String) extends EnumEntry with AttributeValue

object Decoration extends Enumerated[Decoration] with EnumEntryPersistence[Decoration] {
  val None = new Decoration("none")
  val Underline = new Decoration("underline")
  val Overline = new Decoration("overline")
  val LineThrough = new Decoration("line-through")
}
