package org.hyperscala.tags.attributes

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait AttributeValue {
  def value: String

  override def toString = value
}
