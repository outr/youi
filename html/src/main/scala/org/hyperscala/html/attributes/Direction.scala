package org.hyperscala.html.attributes

import org.powerscala.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
sealed class Direction(val value: String) extends EnumEntry[Direction] with AttributeValue

object Direction extends Enumerated[Direction] with EnumEntryPersistence[Direction] {
  val LeftToRight = new Direction("ltr")
  val RightToLeft = new Direction("rtl")
  val Auto = new Direction("auto")
}