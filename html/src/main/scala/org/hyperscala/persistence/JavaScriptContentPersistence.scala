package org.hyperscala.persistence

import org.hyperscala.javascript.{JavaScriptString, JavaScriptContent}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object JavaScriptContentPersistence extends ValuePersistence[JavaScriptContent] {
  def fromString(s: String, name: String, clazz: Class[_]) = JavaScriptString(s)

  def toString(t: JavaScriptContent, name: String, clazz: Class[_]) = if (t != null) {
    t.content
  } else {
    ""
  }
}
