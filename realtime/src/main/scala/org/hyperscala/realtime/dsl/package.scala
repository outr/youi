package org.hyperscala.realtime

import org.hyperscala.event.{KeyboardEvent, Key}
import org.hyperscala.javascript.{JavaScriptString, JSFunction1}
import org.hyperscala.selector.{StringSelector, Selector}

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object dsl {
  def $ = jQueryDSL

  val window = StringSelector("window")
  val body = StringSelector("body")

  def onKey(key: Key,
            altKey: Option[Boolean] = None,
            ctrlKey: Option[Boolean] = None,
            metaKey: Option[Boolean] = None,
            shiftKey: Option[Boolean] = None,
            stopPropagation: Boolean = false)(f: => Unit): JSFunction1[KeyboardEvent, Boolean] = {
    val store = jQueryDSL.callbackStore
    val callback = store.createCallback(() => f)
    val conditional = new StringBuilder
    conditional.append("if (e.keyCode == ")
    conditional.append(key.code)
    def checkConditional(checkOption: Option[Boolean], name: String) = checkOption match {
      case Some(check) => {
        conditional.append(" && ")
        if (!check) {
          conditional.append("!")
        }
        conditional.append("e.")
        conditional.append(name)
      }
      case None => // Don't add anything, we don't care
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
