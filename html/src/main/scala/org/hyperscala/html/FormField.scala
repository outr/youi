package org.hyperscala.html

import constraints.BodyChild
import org.powerscala.property.StandardProperty

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait FormField extends BodyChild {
  def value: StandardProperty[String]
}
