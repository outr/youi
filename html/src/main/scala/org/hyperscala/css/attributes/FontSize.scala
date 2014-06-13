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

  override def apply(name: String) = get(name).getOrElse(throw new RuntimeException(s"FontSize not found for value: $name."))

  override def get(name: String): Option[FontSize] = name.toLowerCase.trim match {
    case null | "" => None
    case "x-small" => Some(XSmall)
    case "small" => Some(Small)
    case "large" => Some(Large)
    case "smaller" => Some(Smaller)
    case "medium" => Some(Medium)
    case "xx-large" => Some(XXLarge)
    case "xx-small" => Some(XXSmall)
    case "auto" => Some(AutoLength)
    case "inherit" => Some(InheritLength)
    case s => NumericLength.get(s)
  }
}