package org.hyperscala.persistence

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object BooleanPersistence extends ValuePersistence[Boolean] {
  def fromString(s: String, clazz: Class[_]) = s != null && s.nonEmpty

  def toString(t: Boolean, clazz: Class[_]) = t match {
    case true => ""
    case false => null
  }
}
