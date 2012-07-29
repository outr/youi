package org.hyperscala.persistence

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
// TODO: verify that nonempty is sufficient!
object BooleanPersistence extends ValuePersistence[Boolean] {
  def fromString(s: String, clazz: Class[_]) = s.nonEmpty

  def toString(t: Boolean, clazz: Class[_]) = t.toString
}
