package org.hyperscala.css.attributes

import org.powerscala.enum.{EnumEntry, Enumerated}
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue

/**
 * Represents CSS length as specified here:
 *
 * https://developer.mozilla.org/en-US/docs/Web/CSS/length
 *
 * @author Matt Hicks <matt@outr.com>
 */
sealed trait Length extends EnumEntryAttributeValue {
  override def toString() = if (name != null) {
    super.toString()
  } else {
    value
  }
}

trait NumericLength extends Length {
  def number: Double
  def lengthType: String

  lazy val value = s"$number$lengthType"
}

object AutoLength extends Length with FontSize {
  val value = "auto"
}

object InheritLength extends Length with FontSize {
  val value = "inherit"
}

case class ChLength(number: Double) extends NumericLength with FontSize {
  def lengthType = "ch"
}

case class CentimeterLength(number: Double) extends NumericLength with FontSize {
  def lengthType = "cm"
}

case class EmLength(number: Double) extends NumericLength with FontSize {
  def lengthType = "em"
}

case class ExLength(number: Double) extends NumericLength with FontSize {
  def lengthType = "ex"
}

case class InchLength(number: Double) extends NumericLength with FontSize {
  def lengthType = "in"
}

case class MillimeterLength(number: Double) extends NumericLength with FontSize {
  def lengthType = "mm"
}

case class PercentLength(number: Double) extends NumericLength with FontSize {
  def lengthType = "%"
}

case class PicaLength(number: Double) extends NumericLength with FontSize {
  def lengthType = "pc"
}

case class PixelLength(number: Double) extends NumericLength with FontSize {
  def lengthType = "px"
}

case class PointLength(number: Double) extends NumericLength with FontSize {
  def lengthType = "pt"
}

case class RemLength(number: Double) extends NumericLength with FontSize {
  def lengthType = "rem"
}

case class ViewportHeightLength(number: Double) extends NumericLength with FontSize {
  def lengthType = "vh"
}

case class ViewportMinimumLength(number: Double) extends NumericLength with FontSize {
  def lengthType = "vmin"
}

case class ViewportMaximumLength(number: Double) extends NumericLength with FontSize {
  def lengthType = "vmax"
}

case class ViewportWidthLength(number: Double) extends NumericLength with FontSize {
  def lengthType = "vw"
}

object Length extends Enumerated[Length] with EnumEntryPersistence[Length] {
  val NumberRegex = """([\d.]+)([%a-zA-Z]*+)""".r

  val Auto = AutoLength
  val Inherit = InheritLength
  def Pixels(v: Double) = PixelLength(v)
  def Centimeters(v: Double) = CentimeterLength(v)
  def Percent(v: Double) = PercentLength(v)

  override def apply(name: String): Length = name.toLowerCase match {
    case null => null
    case "auto" => AutoLength
    case "inherit" => InheritLength
    case NumberRegex(n, t) => t match {
      case null | "" | "px" => PixelLength(n.toDouble)
      case "ch" => ChLength(n.toDouble)
      case "cm" => CentimeterLength(n.toDouble)
      case "em" => EmLength(n.toDouble)
      case "ex" => ExLength(n.toDouble)
      case "in" => InchLength(n.toDouble)
      case "mm" => MillimeterLength(n.toDouble)
      case "%" => PercentLength(n.toDouble)
      case "pc" => PicaLength(n.toDouble)
      case "pt" => PointLength(n.toDouble)
      case "rem" => RemLength(n.toDouble)
      case "vh" => ViewportHeightLength(n.toDouble)
      case "vmin" => ViewportMinimumLength(n.toDouble)
      case "vmax" => ViewportMaximumLength(n.toDouble)
      case "vw" => ViewportWidthLength(n.toDouble)
    }
  }
}

class LengthType private(val value: String) extends EnumEntry

object LengthType extends Enumerated[LengthType] {
  val Ch = new LengthType("ch")
  val Centimeter = new LengthType("cm")
  val Em = new LengthType("em")
  val Ex = new LengthType("ex")
  val Inch = new LengthType("in")
  val Millimeter = new LengthType("mm")
  val Pica = new LengthType("pc")
  val Pixel = new LengthType("px")
  val Point = new LengthType("pt")
  val Rem = new LengthType("rem")
  val ViewportHeight = new LengthType("vh")
  val ViewportMinimum = new LengthType("vmin")
  val ViewportMaximum = new LengthType("vmax")
  val ViewportWidth = new LengthType("vw")
}