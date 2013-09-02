package org.hyperscala.css.extra

import org.powerscala.property.{DerivedProperty, Property}
import org.hyperscala.css.attributes._
import org.hyperscala.css.StyleSheet
import org.powerscala.event.Listenable
import org.powerscala.Color

/**
 * @author Matt Hicks <matt@outr.com>
 */
class TextShadowColor(ss: StyleSheet) extends Property[Color]()(ss, implicitly[Manifest[Color]]) with DerivedProperty[Color, TextShadow] {
  implicit def styleSheetListenable: Listenable = ss

  def other = ss.textShadow

  def fromT(value: Color) = {
    val ts = other.value
    if (ts == null) {
      TextShadow(color = value)
    } else {
      ts.copy(color = value)
    }
  }

  def fromO(value: TextShadow) = if (value == null) {
    null
  } else {
    value.color
  }
}