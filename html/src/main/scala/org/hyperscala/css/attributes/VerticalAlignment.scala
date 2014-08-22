package org.hyperscala.css.attributes

import org.hyperscala.EnumEntryAttributeValue
import org.hyperscala.persistence.EnumEntryPersistence
import org.powerscala.enum.Enumerated

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait VerticalAlignment extends EnumEntryAttributeValue {
  override def toString() = if (name != null) {
    super.toString()
  } else {
    value
  }
}

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

object VerticalAlignment extends Enumerated[VerticalAlignment] with EnumEntryPersistence[VerticalAlignment] {
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

  override def apply(name: String) = get(name).getOrElse(throw new RuntimeException(s"VerticalAlignment not found for value: $name."))

  override def get(name: String, caseSensitive: Boolean = false) = super.get(name, caseSensitive) match {
    case Some(l) => Some(l)
    case None if name == null => None
    case None => name.toLowerCase match {
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
}