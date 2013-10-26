package org.hyperscala.persistence

import com.outr.net.Method

/**
 * @author Matt Hicks <matt@outr.com>
 */
object MethodPersistence extends ValuePersistence[Method] {
  def fromString(s: String, name: String, clazz: Class[_]) = Method(s)

  def toString(t: Method, name: String, clazz: Class[_]) = if (t != null) {
    t.value
  } else {
    null
  }
}