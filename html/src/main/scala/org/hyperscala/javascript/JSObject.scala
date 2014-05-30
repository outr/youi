package org.hyperscala.javascript

import org.powerscala.reflect.CaseValue

/**
 * Flag mix-in to tell JavaScriptContent when converting an instance to convert it from a case-class to a JSON represented
 * object.
 *
 * @author Matt Hicks <matt@outr.com>
 */
trait JSObject {
  protected def fieldName(cv: CaseValue): Option[String] = Some(cv.name)
}

object JSObject {
  def fieldName(jso: JSObject, cv: CaseValue) = jso.fieldName(cv)
}