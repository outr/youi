package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.AttributeValue
import org.hyperscala.persistence.EnumEntryPersistence

/**
 * @author Matt Hicks <matt@outr.com>
 */
class LineStyle private(val value: String) extends EnumEntry with AttributeValue

object LineStyle extends Enumerated[LineStyle] with EnumEntryPersistence[LineStyle] {
  val None = new LineStyle("none")
  val Hidden = new LineStyle("hidden")
  val Dotted = new LineStyle("dotted")
  val Dashed = new LineStyle("dashed")
  val Solid = new LineStyle("solid")
  val Double = new LineStyle("double")
  val Groove = new LineStyle("groove")
  val Ridge = new LineStyle("ridge")
  val Inset = new LineStyle("inset")
  val Outset = new LineStyle("outset")
  val Inherit = new LineStyle("inherit")
}