package org.hyperscala.realtime

import org.hyperscala.event.{KeyboardEvent, Key}
import org.hyperscala.javascript.{JavaScriptString, JSFunction1}
import org.hyperscala.selector.Selector

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object dsl {
  def $ = jQueryDSL

  def onKey(key: Key,
            altKey: Boolean = false,
            ctrlKey: Boolean = false,
            metaKey: Boolean = false,
            shiftKey: Boolean = false,
            stopPropagation: Boolean = false)(f: => Unit): JSFunction1[KeyboardEvent, Boolean] = {
    val store = jQueryDSL.callbackStore
    val callback = store.createCallback(() => f)
    val conditional = new StringBuilder
    conditional.append("if (e.keyCode == ")
    conditional.append(key.code)
    def checkConditional(check: Boolean, name: String) = if (check) {
      conditional.append(" && e.")
      conditional.append(name)
    }
    checkConditional(altKey, "altKey")
    checkConditional(ctrlKey, "ctrlKey")
    checkConditional(metaKey, "metaKey")
    checkConditional(shiftKey, "shiftKey")
    conditional.append(")")
    new JavaScriptString(s"function(e) { ${conditional.toString()} { ${callback.content} return ${!stopPropagation}; } }") with JSFunction1[KeyboardEvent, Boolean]
  }

  def addClass(selector: Selector, className: String) = AddClassJavaScriptFunction(selector, className)

  def setValue(selector: Selector) = SetValueJavaScriptFunction(selector)
}
