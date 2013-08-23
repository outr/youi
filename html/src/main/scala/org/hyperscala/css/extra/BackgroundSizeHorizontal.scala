package org.hyperscala.css.extra

import org.powerscala.property.{DerivedProperty, Property}
import org.hyperscala.css.attributes._
import org.hyperscala.css.StyleSheetBase
import org.powerscala.event.Listenable

/**
 * @author Matt Hicks <matt@outr.com>
 */
class BackgroundSizeHorizontal(ss: StyleSheetBase) extends Property[Length]()(ss, implicitly[Manifest[Length]]) with DerivedProperty[Length, BackgroundSize] {
  implicit def styleSheetListenable: Listenable = ss

  def other = ss.backgroundSize

  def fromT(value: Length) = {
    val bs = other.value
    if (bs == null) {
      BackgroundSize(horizontal = value)
    } else {
      bs.copy(horizontal = value)
    }
  }

  def fromO(value: BackgroundSize) = if (value == null) {
    null
  } else {
    value.horizontal
  }
}