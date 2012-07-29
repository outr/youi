package org.hyperscala.css.attributes

import org.powerscala.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
sealed class Alignment(val value: String) extends EnumEntry[Alignment] with AttributeValue

object Alignment extends Enumerated[Alignment] with EnumEntryPersistence[Alignment] {
  val Justify = new Alignment("justify")
  val Center = new Alignment("center")
  val Inherit = new Alignment("inherit")
  val Left = new Alignment("left")
  val Right = new Alignment("right")
}