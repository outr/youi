package org.hyperscala.ui.widgets.editable

import org.hyperscala.html._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait InputEditable {
  this: Editable[_, tag.Input] =>

  val editor = new tag.Input
}