package org.hyperscala.persistence

import org.hyperscala.javascript.{JavaScriptString, JavaScriptContent}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
object JavaScriptContentPersistence extends ValuePersistence[JavaScriptContent] {
  def fromString(s: String, clazz: Class[_]) = JavaScriptString(s)

  def toString(t: JavaScriptContent, clazz: Class[_]) = t.content
}
