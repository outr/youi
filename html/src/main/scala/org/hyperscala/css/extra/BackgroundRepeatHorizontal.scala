package org.hyperscala.css.extra

import org.powerscala.property.{DerivedProperty, Property}
import org.hyperscala.css.attributes.{BackgroundRepeat, Repeat}
import org.hyperscala.css.StyleSheetBase
import org.powerscala.event.Listenable

/**
 * @author Matt Hicks <matt@outr.com>
 */
class BackgroundRepeatHorizontal(ss: StyleSheetBase) extends Property[Repeat]()(ss, implicitly[Manifest[Repeat]]) with DerivedProperty[Repeat, BackgroundRepeat] {
  implicit def styleSheetListenable: Listenable = ss

  def other = ss.backgroundRepeat

  def fromT(value: Repeat) = {
    val br = other.value
    if (br == null) {
      BackgroundRepeat(value, value)
    } else {
      br.copy(horizontal = value)
    }
  }

  def fromO(value: BackgroundRepeat) = if (value == null) {
    null
  } else {
    value.horizontal
  }
}