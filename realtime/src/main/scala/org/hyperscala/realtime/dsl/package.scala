package org.hyperscala.realtime

import org.hyperscala.event.{KeyboardEvent, Key}
import org.hyperscala.selector.Selector
import org.hyperscala.css.StyleSheetAttribute
import org.hyperscala.PropertyAttribute
import org.hyperscala.javascript.dsl._
import org.hyperscala.jquery.dsl._

import scala.language.implicitConversions
import org.hyperscala.web.Webpage
import com.outr.net.http.session.Session

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object dsl {
  implicit def statement2RealtimeStatement(statement: Statement[_]) = RealtimeStatement(statement)

  def onKey[S <: Session](webpage: Webpage[S],
            key: Key,
            altKey: Option[Boolean] = None,
            ctrlKey: Option[Boolean] = None,
            metaKey: Option[Boolean] = None,
            shiftKey: Option[Boolean] = None,
            stopPropagation: Boolean = false)(f: => Unit): JSFunction1[KeyboardEvent, Boolean] = {
    val store = CallbackStore(webpage)
    val callback = store.createCallback(() => f)
    val conditional = new StringBuilder
    conditional.append("if (p1.keyCode == ")
    conditional.append(key.code)
    def checkConditional(checkOption: Option[Boolean], name: String) = checkOption match {
      case Some(check) => {
        conditional.append(" && ")
        if (!check) {
          conditional.append("!")
        }
        conditional.append("p1.")
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

  def addClassToParent(selector: Selector, className: String) = {
    val js =
      s"""
        |if (p1) {
        | $$(parent.document).find(${selector.content}).addClass('$className');
        |} else {
        | $$(parent.document).find(${selector.content}).removeClass('$className');
        |}
      """.stripMargin
    JSFunction1[Boolean, Unit](js)
  }

  def addClass(selector: Selector, className: String) = {
    val js =
      s"""
        |if (p1) {
        | $$(${selector.content}).addClass('$className');
        |} else {
        | $$(${selector.content}).removeClass('$className');
        |}
      """.stripMargin
    JSFunction1[Boolean, Unit](js)
  }

  def setValue(selector: Selector) = new JSFunction1[String, Unit] {
    $(selector).value(p1)
  }

  def setValueToParent(selector: Selector) = {
    val js = s"""$$(parent.document).find(${selector.content}).val(p1);"""
    JSFunction1[String, Unit](js)
  }
}
