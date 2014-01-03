package org.hyperscala.ui.widgets.visual.types

import org.hyperscala.ui.widgets.visual.VisualBuilder

/**
 * @author Matt Hicks <matt@outr.com>
 */
object StringInputVisualType extends InputVisualType[String] {
  def valid(details: VisualBuilder[_]) = details.clazz == classOf[String] && details.selection == null && !details.multiLine

  def fromString(s: String) = s

  def toString(t: String) = t
}
