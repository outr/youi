package org.hyperscala.css.extra

import org.powerscala.property.{DerivedProperty, Property}
import org.hyperscala.css.attributes.{BackgroundRepeat, Repeat}
import org.hyperscala.css.StyleSheet
import org.powerscala.event.Listenable

/**
 * @author Matt Hicks <matt@outr.com>
 */
class BackgroundRepeatVertical(ss: StyleSheet) extends Property[Repeat]()(ss, implicitly[Manifest[Repeat]]) with DerivedProperty[Repeat, BackgroundRepeat] {
  implicit def styleSheetListenable: Listenable = ss

  def other = ss.backgroundRepeat

  def fromT(value: Repeat) = {
    val br = other.value
    if (br == null) {
      BackgroundRepeat(value, value)
    } else {
      br.copy(vertical = value)
    }
  }

  def fromO(value: BackgroundRepeat) = if (value == null) {
    null
  } else {
    value.vertical
  }
}