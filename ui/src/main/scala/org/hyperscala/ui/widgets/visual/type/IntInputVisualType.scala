package org.hyperscala.ui.widgets.visual.`type`

import org.hyperscala.ui.widgets.visual.VisualBuilder

/**
 * @author Matt Hicks <matt@outr.com>
 */
object IntInputVisualType extends InputVisualType[Int] {
  def valid(details: VisualBuilder[_]) = details.clazz == classOf[Int]

  def fromString(s: String) = if (s == null || s == "") {
    0
  } else {
    s.collect {
      case c if (c.isDigit) => c
    }.toInt
  }

  def toString(t: Int) = t.toString
}
