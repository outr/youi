package org.hyperscala.css.attributes

import org.powerscala.enum.Enumerated
import org.hyperscala.persistence.EnumEntryPersistence
import org.hyperscala.EnumEntryAttributeValue
import scala.util.matching.Regex

/**
 * Represents CSS font-size as represented here:
 *
 * https://developer.mozilla.org/en-US/docs/Web/CSS/font-size
 *
 * @author Matt Hicks <matt@outr.com>
 */
trait FontSize extends EnumEntryAttributeValue {
  override def toString() = if (name != null) {
    super.toString()
  } else {
    value
  }
}

object XSmallFontSize extends FontSize {
  val value = "x-small"
}
object SmallFontSize extends FontSize {
  val value = "small"
}

object LargeFontSize extends FontSize {
  val value = "Large"
}

object SmallerFontSize extends FontSize {
  val value = "smaller"
}

object MediumFontSize extends FontSize {
  val value = "medium"
}

object XXLargeFontSize extends FontSize {
  val value = "xx-large"
}

object XXSmalFontSize extends FontSize {
  val value = "xx-small"
}

object LargerFontSize extends FontSize {
  val value = "larger"
}

object XLargeFontSize extends FontSize {
  val value = "x-large"
}

object FontSize extends Enumerated[FontSize] with EnumEntryPersistence[FontSize] {
  val XSmall = XSmallFontSize
  val Small = SmallFontSize
  val Large = LargeFontSize
  val Smaller = SmallerFontSize
  val Medium = MediumFontSize
  val XXLarge = XXLargeFontSize
  val XXSmall = XXSmalFontSize
  val Inherit = InheritLength
  val Larger = LargerFontSize
  val XLarge = XLargeFontSize
  def Pixels(v: Double) = PixelLength(v)
  def Centimeters(v: Double) = CentimeterLength(v)
  def Percent(v: Double) = PercentLength(v)
  def Points(v: Double) = PointLength(v)

  override def apply(name: String): FontSize = name.toLowerCase match {
    case "x-small" => XSmall
    case "small" => Small
    case "large" => Large
    case "smaller" => Smaller
    case "medium" => Medium
    case "xx-large" => XXLarge
    case "xx-small" => XXSmall
    case null => null
    case "auto" => AutoLength
    case "inherit" => InheritLength
    case Length.NumberRegex(n, t) => t match {
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