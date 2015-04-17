package org.hyperscala.css.attributes

import org.hyperscala.{AttributeValue, EnumEntryAttributeValue}
import org.hyperscala.persistence.{CaseClassPersistence, EnumEntryPersistence}
import org.powerscala.enum.Enumerated

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait VerticalAlignment extends AttributeValue

object BaselineVerticalAlignment extends VerticalAlignment {
  val value = "baseline"
}

object SubVerticalAlignment extends VerticalAlignment {
  val value = "sub"
}

object SuperVerticalAlignment extends VerticalAlignment {
  val value = "super"
}

object TextTopVerticalAlignment extends VerticalAlignment {
  val value = "text-top"
}

object TextBottomVerticalAlignment extends VerticalAlignment {
  val value = "text-bottom"
}

object MiddleVerticalAlignment extends VerticalAlignment {
  val value = "middle"
}

object TopVerticalAlignment extends VerticalAlignment {
  val value = "top"
}

object BottomVerticalAlignment extends VerticalAlignment {
  val value = "bottom"
}

object VerticalAlignment extends CaseClassPersistence[VerticalAlignment] {
  val Baseline = BaselineVerticalAlignment
  val Sub = SubVerticalAlignment
  val Super = SuperVerticalAlignment
  val TextTop = TextTopVerticalAlignment
  val TextBottom = TextBottomVerticalAlignment
  val Middle = MiddleVerticalAlignment
  val Top = TopVerticalAlignment
  val Bottom = BottomVerticalAlignment
  val Inherit = InheritLength
  def Pixels(v: Double) = PixelLength(v)
  def Centimeters(v: Double) = CentimeterLength(v)
  def Percent(v: Double) = PercentLength(v)
  def Points(v: Double) = PointLength(v)

  def apply(name: String) = get(name).getOrElse(throw new RuntimeException(s"VerticalAlignment not found for value: $name."))

  def get(name: String) = name.toLowerCase match {
    case Baseline.value => Some(Baseline)
    case Sub.value => Some(Sub)
    case Super.value => Some(Super)
    case TextTop.value => Some(TextTop)
    case TextBottom.value => Some(TextBottom)
    case Middle.value => Some(Middle)
    case Top.value => Some(Top)
    case Bottom.value => Some(Bottom)
    case "auto" => Some(AutoLength)
    case "inherit" => Some(InheritLength)
    case _ => NumericLength.get(name.toLowerCase)
  }
}