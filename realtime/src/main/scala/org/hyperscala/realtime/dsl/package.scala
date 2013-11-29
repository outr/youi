package org.hyperscala.realtime

import org.hyperscala.event.{KeyboardEvent, Key}
import org.hyperscala.selector.{StringSelector, Selector}
import org.hyperscala.css.StyleSheetAttribute
import org.hyperscala.PropertyAttribute
import org.hyperscala.javascript.dsl.{JSFunction0, JSFunction1, Statement}

import scala.language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object dsl {
  implicit def statement2RealtimeStatement(statement: Statement[_]) = RealtimeStatement(statement)

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
    JSFunction1[KeyboardEvent, Boolean](s"${conditional.toString()} { ${callback.content} return ${!stopPropagation}; }")
  }

  def onCSS[T](selector: Selector, attribute: StyleSheetAttribute[T]) = {
    val key = attribute.name
    JSFunction0[T](s"return $$('${selector.value}').css('$key');")(attribute.manifest)
  }

  def onAttribute[T](selector: Selector, attribute: PropertyAttribute[T]) = {
    val key = attribute.name
    JSFunction0[T](s"return $$('${selector.value}').attr('$key');")(attribute.manifest)
  }

  def addClass(selector: Selector, className: String) = {
    val js =
      """
        |if (b) {
        | $$(${selector.value}).addClass('$className');
        |} else {
        | $$(${selector.value}).removeClass('$className');
        |}
      """.stripMargin
    JSFunction1[Boolean, Unit](js)
  }

  def setValue(selector: Selector) = {
    JSFunction1[String, Unit](s"$$(${selector.value}).val(value);")
  }
}
