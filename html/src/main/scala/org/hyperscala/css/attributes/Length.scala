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

trait NumericLength extends Length with FontSize {
  def number: Double
  def lengthType: String

  lazy val value = s"$number$lengthType"
}

object NumericLength {
  val Regex = """([\d.]+)([%a-zA-Z]*+)""".r

  def apply(name: String) = get(name).getOrElse(throw new RuntimeException(s"NumericLength not found for value: $name."))

  def get(name: String) = name match {
    case Regex(n, t) => t match {
      case null | "" | "px" => Some(PixelLength(n.toDouble))
      case "ch" => Some(ChLength(n.toDouble))
      case "cm" => Some(CentimeterLength(n.toDouble))
      case "em" => Some(EmLength(n.toDouble))
      case "ex" => Some(ExLength(n.toDouble))
      case "in" => Some(InchLength(n.toDouble))
      case "mm" => Some(MillimeterLength(n.toDouble))
      case "%" => Some(PercentLength(n.toDouble))
      case "pc" => Some(PicaLength(n.toDouble))
      case "pt" => Some(PointLength(n.toDouble))
      case "rem" => Some(RemLength(n.toDouble))
      case "vh" => Some(ViewportHeightLength(n.toDouble))
      case "vmin" => Some(ViewportMinimumLength(n.toDouble))
      case "vmax" => Some(ViewportMaximumLength(n.toDouble))
      case "vw" => Some(ViewportWidthLength(n.toDouble))
    }
  }
}

object AutoLength extends Length with FontSize {
  val value = "auto"
}

object InheritLength extends Length with FontSize {
  val value = "inherit"
}

case class ChLength(number: Double) extends NumericLength {
  def lengthType = "ch"
}

case class CentimeterLength(number: Double) extends NumericLength {
  def lengthType = "cm"
}

case class EmLength(number: Double) extends NumericLength {
  def lengthType = "em"
}

case class ExLength(number: Double) extends NumericLength {
  def lengthType = "ex"
}

case class InchLength(number: Double) extends NumericLength {
  def lengthType = "in"
}

case class MillimeterLength(number: Double) extends NumericLength {
  def lengthType = "mm"
}

case class PercentLength(number: Double) extends NumericLength {
  def lengthType = "%"
}

case class PicaLength(number: Double) extends NumericLength {
  def lengthType = "pc"
}

case class PixelLength(number: Double) extends NumericLength {
  def lengthType = "px"
}

case class PointLength(number: Double) extends NumericLength {
  def lengthType = "pt"
}

case class RemLength(number: Double) extends NumericLength {
  def lengthType = "rem"
}

case class ViewportHeightLength(number: Double) extends NumericLength {
  def lengthType = "vh"
}

case class ViewportMinimumLength(number: Double) extends NumericLength {
  def lengthType = "vmin"
}

case class ViewportMaximumLength(number: Double) extends NumericLength {
  def lengthType = "vmax"
}

case class ViewportWidthLength(number: Double) extends NumericLength {
  def lengthType = "vw"
}

object Length extends Enumerated[Length] with EnumEntryPersistence[Length] {
  val Auto = AutoLength
  val Inherit = InheritLength
  def Pixels(v: Double) = PixelLength(v)
  def Centimeters(v: Double) = CentimeterLength(v)
  def Percent(v: Double) = PercentLength(v)

  override def apply(name: String) = get(name).getOrElse(throw new RuntimeException(s"Length not found for value: $name."))

  override def get(name: String, caseSensitive: Boolean = false) = super.get(name, caseSensitive) match {
    case Some(l) => Some(l)
    case None if name == null => None
    case None => NumericLength.get(name.toLowerCase)
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