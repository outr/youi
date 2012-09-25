package org.hyperscala.ui.widgets.editable

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait StringEditable {
  this: Editable[String, _] =>

  def toString(value: String) = value

  def fromString(s: String) = s
}
