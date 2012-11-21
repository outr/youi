package org.hyperscala.ui.widgets.visual.`type`

import org.hyperscala.ui.widgets.visual.VisualDetails

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object StringInputVisualType extends InputVisualType[String] {
  def valid(details: VisualDetails[_]) = details.clazz == classOf[String] && details.selection.isEmpty && !details.multiLine

  def fromString(s: String) = s

  def toString(t: String) = t
}
