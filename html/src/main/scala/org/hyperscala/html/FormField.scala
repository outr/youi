package org.hyperscala.html

import constraints.BodyChild
import org.powerscala.property.StandardProperty
import org.hyperscala.PropertyAttribute

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait FormField extends BodyChild {
  def disabled: PropertyAttribute[Boolean]
  def value: StandardProperty[String]
}
