package org.hyperscala.css.attributes

import org.powerscala.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
class FontSize(val value: String) extends EnumEntry[FontSize] with AttributeValue

object FontSize extends Enumerated[FontSize] with EnumEntryPersistence[FontSize] {
  val XSmall = new FontSize("x-small")
  val Small = new FontSize("small")
  val Large = new FontSize("large")
  val Smaller = new FontSize("smaller")
  val Medium = new FontSize("medium")
  val XXLarge = new FontSize("xx-large")
  val XXSmall = new FontSize("xx-small")
  val Inherit = new FontSize("inherit")
  val Larger = new FontSize("larger")
  val XLarge = new FontSize("x-large")
  def Pixels(v: Int) = Length("%spx".format(v))
  def Centimeters(v: Int) = Length("%scm".format(v))
  def Percent(v: Int) = Length(v + "%")

  override def apply(name: String) = super.apply(name) match {
    case null => new FontSize(name)
    case v => v
  }
}