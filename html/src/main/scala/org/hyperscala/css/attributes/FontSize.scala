package org.hyperscala.css.attributes

import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.AttributeValue

/**
 * NOTE: This file has been generated. Do not modify directly!
 * @author Matt Hicks <mhicks@hyperscala.org>
 */
class FontSize(val value: String) extends EnumEntry with AttributeValue

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
  def Pixels(v: Int) = new FontSize("%spx".format(v))
  def Centimeters(v: Int) = new FontSize("%scm".format(v))
  def Percent(v: Int) = new FontSize(v + "%")
  def Points(v: Int) = new FontSize("%spt".format(v))

  override def apply(name: String): FontSize = name match {
    case null => null
    case Length.NumberRegex(n) => Pixels(n.toInt)
    case _ => super.apply(name) match {
      case null => new FontSize(name)
      case v => v
    }
  }
}