package org.hyperscala.html.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed class Direction(val value: String) extends EnumEntryAttributeValue

object Direction extends Enumerated[Direction] with EnumEntryPersistence[Direction] {
  val LeftToRight = new Direction("ltr")
  val RightToLeft = new Direction("rtl")
  val Auto = new Direction("auto")
}