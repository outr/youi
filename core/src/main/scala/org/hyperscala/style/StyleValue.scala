package org.hyperscala.style

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait StyleValue {
  def value: String

  override def toString = value
}
