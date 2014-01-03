package org.hyperscala.ui.widgets.visual.types

import org.hyperscala.ui.widgets.visual.VisualBuilder

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
object FloatInputVisualType extends InputVisualType[Float] {
  def valid(details: VisualBuilder[_]) = details.clazz == classOf[Float]

  def fromString(s: String) = if (s == null || s == "") {
    0
  } else {
    var d = false
    s.collect {
      case c if (c.isDigit) => c
      case c if (c == '.' && !d) => {
        d = true
        c
      }
    }.toFloat
  }

  def toString(t: Float) = t.toString
}