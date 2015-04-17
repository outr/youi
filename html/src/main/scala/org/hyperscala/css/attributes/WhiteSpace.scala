package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class WhiteSpace(val value: String) extends EnumEntryAttributeValue

object WhiteSpace extends Enumerated[WhiteSpace] with EnumEntryPersistence[WhiteSpace] {
  case object Normal extends WhiteSpace("normal")
  case object NoWrap extends WhiteSpace("nowrap")
  case object Pre extends WhiteSpace("pre")
  case object PreWrap extends WhiteSpace("pre-wrap")
  case object PreLine extends WhiteSpace("pre-line")
  case object Inherit extends WhiteSpace("inherit")

  val values = findValues.toVector
}