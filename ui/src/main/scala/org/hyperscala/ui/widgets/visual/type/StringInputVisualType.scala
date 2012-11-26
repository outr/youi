package org.hyperscala.ui.widgets.visual.`type`

import org.hyperscala.ui.widgets.visual.VisualBuilder

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object StringInputVisualType extends InputVisualType[String] {
  def valid(details: VisualBuilder[_]) = details.clazz == classOf[String] && details.selection.isEmpty && !details.multiLine

  def fromString(s: String) = s

  def toString(t: String) = t
}
