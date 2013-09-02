package org.hyperscala.css.extra

import org.powerscala.property.{DerivedProperty, Property}
import org.hyperscala.css.attributes._
import org.hyperscala.css.StyleSheet
import org.powerscala.event.Listenable

/**
 * @author Matt Hicks <matt@outr.com>
 */
class TextShadowOffsetY(ss: StyleSheet) extends Property[Length]()(ss, implicitly[Manifest[Length]]) with DerivedProperty[Length, TextShadow] {
  implicit def styleSheetListenable: Listenable = ss

  def other = ss.textShadow

  def fromT(value: Length) = {
    val ts = other.value
    if (ts == null) {
      TextShadow(offsetY = value)
    } else {
      ts.copy(offsetY = value)
    }
  }

  def fromO(value: TextShadow) = if (value == null) {
    null
  } else {
    value.offsetY
  }
}