package org.hyperscala.realtime

import org.hyperscala.event.{KeyboardEvent, Key}
import org.hyperscala.javascript.{JSFunction0, JavaScriptString, JSFunction1}
import org.hyperscala.selector.{StringSelector, Selector}
import org.hyperscala.css.StyleSheetAttribute
import org.hyperscala.PropertyAttribute
import org.hyperscala.javascript.dsl.Statement

import scala.language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object dsl {
  implicit def statement2RealtimeStatement(statement: Statement) = RealtimeStatement(statement)

  val window = StringSelector("window")
  val body = StringSelector("body")

  def onKey(key: Key,
            altKey: Option[Boolean] = None,
            ctrlKey: Option[Boolean] = None,
            metaKey: Option[Boolean] = None,
            shiftKey: Option[Boolean] = None,
            stopPropagation: Boolean = false)(f: => Unit): JSFunction1[KeyboardEvent, Boolean] = {
    val store = CallbackStore()
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

  def onCSS[T](selector: Selector, attribute: StyleSheetAttribute[T]) = {
    val key = attribute.name
    new JavaScriptString(s"function() { return $$('${selector.value}').css('$key'); }") with JSFunction0[T]
  }

  def onAttribute[T](selector: Selector, attribute: PropertyAttribute[T]) = {
    val key = attribute.name
    new JavaScriptString(s"function() { return $$('${selector.value}').attr('$key'); }") with JSFunction0[T]
  }

  def addClass(selector: Selector, className: String) = AddClassJavaScriptFunction(selector, className)

  def setValue(selector: Selector) = SetValueJavaScriptFunction(selector)
}
