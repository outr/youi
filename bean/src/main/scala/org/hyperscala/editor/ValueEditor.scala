package org.hyperscala.editor

import org.powerscala.property.StandardProperty
import org.hyperscala.html.constraints.BodyChild

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait ValueEditor[T] extends BodyChild {
  def property: StandardProperty[T]
}