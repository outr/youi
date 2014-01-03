package org.hyperscala.persistence

import org.hyperscala.javascript.{JavaScriptString, JavaScriptContent}

/**
 * @author Matt Hicks <matt@outr.com>
 */
object JavaScriptContentPersistence extends ValuePersistence[JavaScriptContent] {
  def fromString(s: String, name: String, clazz: Class[_]) = JavaScriptString(s)

  def toString(t: JavaScriptContent, name: String, clazz: Class[_]) = if (t != null) {
    t.content
  } else {
    ""
  }
}
