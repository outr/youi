package org.hyperscala.realtime

import org.hyperscala.event.{KeyUpEvent, Key}
import org.hyperscala.javascript.{JavaScriptString, JSFunction1}
import org.hyperscala.selector.Selector

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object dsl {
  def $ = jQueryDSL

  def onKey(key: Key, stopPropagation: Boolean = false)(f: => Unit): JSFunction1[KeyUpEvent, Boolean] = {
    val store = jQueryDSL.callbackStore
    val callback = store.createCallback(() => f)
    new JavaScriptString(s"function(e) { if (e.keyCode == ${key.code}) { ${callback.content} return ${!stopPropagation}; } }") with JSFunction1[KeyUpEvent, Boolean]
  }

  def addClass(selector: Selector, className: String) = AddClassJavaScriptFunction(selector, className)
}
