package org.hyperscala.css.extra

import org.powerscala.property.{DerivedProperty, Property}
import org.hyperscala.css.attributes._
import org.hyperscala.css.StyleSheetBase
import org.powerscala.event.Listenable

/**
 * @author Matt Hicks <matt@outr.com>
 */
class BackgroundPositionHorizontal(ss: StyleSheetBase) extends Property[Horizontal]()(ss, implicitly[Manifest[Horizontal]]) with DerivedProperty[Horizontal, BackgroundPosition] {
  def other = ss.backgroundPosition

  def fromT(value: Horizontal) = {
    val br = other.value
    if (br == null) {
      BackgroundPosition(horizontal = value)
    } else {
      br.copy(horizontal = value)
    }
  }

  def fromO(value: BackgroundPosition) = if (value == null) {
    null
  } else {
    value.horizontal
  }
}