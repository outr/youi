package org.hyperscala.css.extra

import org.powerscala.property.{DerivedProperty, Property}
import org.hyperscala.css.attributes._
import org.hyperscala.css.StyleSheetBase
import org.powerscala.event.Listenable

/**
 * @author Matt Hicks <matt@outr.com>
 */
class BackgroundPositionVertical(ss: StyleSheetBase) extends Property[Vertical]()(ss, implicitly[Manifest[Vertical]]) with DerivedProperty[Vertical, BackgroundPosition] {
  implicit def styleSheetListenable: Listenable = ss

  def other = ss.backgroundPosition

  def fromT(value: Vertical) = {
    val br = other.value
    if (br == null) {
      BackgroundPosition(vertical = value)
    } else {
      br.copy(vertical = value)
    }
  }

  def fromO(value: BackgroundPosition) = if (value == null) {
    null
  } else {
    value.vertical
  }
}