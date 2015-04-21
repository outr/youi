package org.hyperscala.persistence

import org.hyperscala.AttributeValue

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait CaseClassPersistence[E <: AttributeValue] extends ValuePersistence[E] {
  def apply(value: String): E

  def fromString(s: String, name: String, clazz: Class[_]) = {
    apply(s)
  }

  def toString(e: E, name: String, clazz: Class[_]) = if (e != null) {
    e.value
  } else {
    null
  }
}
