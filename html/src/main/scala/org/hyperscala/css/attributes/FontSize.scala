package org.hyperscala.css.attributes

import org.hyperscala.persistence.ValuePersistence
import org.hyperscala.AttributeValue

/**
 * Represents CSS font-size as represented here:
 *
 * https://developer.mozilla.org/en-US/docs/Web/CSS/font-size
 *
 * @author Matt Hicks <matt@outr.com>
 */
trait FontSize extends AttributeValue {
  override def toString = value
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

object FontSize extends ValuePersistence[FontSize] {
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

  override def fromString(s: String, name: String, clazz: Class[_]) = apply(s)

  override def toString(t: FontSize, name: String, clazz: Class[_]) = t.value

  def apply(name: String) = get(name).getOrElse(throw new RuntimeException(s"FontSize not found for value: $name."))

  def get(name: String) = if (name != null) {
    name.toLowerCase match {
      case "x-small" => Some(XSmall)
      case "small" => Some(Small)
      case "large" => Some(Large)
      case "smaller" => Some(Smaller)
      case "medium" => Some(Medium)
      case "xx-large" => Some(XXLarge)
      case "xx-small" => Some(XXSmall)
      case "auto" => Some(AutoLength)
      case "inherit" => Some(InheritLength)
      case _ => NumericLength.get(name.toLowerCase)
    }
  } else {
    None
  }
}