package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
sealed abstract class LineStyle(val value: String) extends EnumEntryAttributeValue

object LineStyle extends Enumerated[LineStyle] with EnumEntryPersistence[LineStyle] {
  case object None extends LineStyle("none")
  case object Hidden extends LineStyle("hidden")
  case object Dotted extends LineStyle("dotted")
  case object Dashed extends LineStyle("dashed")
  case object Solid extends LineStyle("solid")
  case object Double extends LineStyle("double")
  case object Groove extends LineStyle("groove")
  case object Ridge extends LineStyle("ridge")
  case object Inset extends LineStyle("inset")
  case object Outset extends LineStyle("outset")
  case object Inherit extends LineStyle("inherit")

  val values = findValues.toVector
}