package org.hyperscala.jquery.dsl

import org.hyperscala.selector.Selector
import org.hyperscala.javascript.dsl.{JSFunction1, WrappedStatement, ExistingStatement, Statement}
import org.hyperscala.css.Style
import org.hyperscala.javascript.{JavaScriptString, JavaScriptContent}
import org.hyperscala.event.KeyboardEvent

/**
 * @author Matt Hicks <matt@outr.com>
 */
class jQuerySelector(val selector: Selector) extends Statement[Selector] {
  def content = s"$$(${selector.content})"
  def sideEffects = false

  def scrollTop(offset: Int) = call(s"scrollTop($offset)")

  def blur() = call("blur()")
  def change() = call("change()")
  def focus() = call("focus()")
  def select() = call("select()")
  def submit() = call("submit()")

  def width() = ExistingStatement[Double](s"$content.width()")
  def height() = ExistingStatement[Double](s"$content.height()")

  def keyDown(f: JSFunction1[KeyboardEvent, Boolean]) = {
    WrappedStatement[Unit](s"$content.keydown(", f, ")", sideEffects = true)
  }

  def keyPress(f: JSFunction1[KeyboardEvent, Boolean]) = {
    WrappedStatement[Unit](s"$content.keypress(", f, ")", sideEffects = true)
  }

  def keyUp(f: JSFunction1[KeyboardEvent, Boolean]) = {
    WrappedStatement[Unit](s"$content.keyup(", f, ")", sideEffects = true)
  }

  def value[T]() = ExistingStatement[T](s"$content.val()")
  def value[T](s: Statement[T]) = WrappedStatement[T](s"$content.val(", s, ")", sideEffects = true)

  def offset() = new jQueryOffset(this)
  def position() = new jQueryPosition(this)

  def css[S](style: Style[S]) = ExistingStatement[S](s"$content.css('${style.cssName}')")

  def css[S](style: Style[S], value: Statement[S]) = WrappedStatement[S](s"$content.css('${style.cssName}', ", value, ")", sideEffects = true)

  def call(function: String): Statement[Unit] = ExistingStatement[Unit](s"$content.$function", sideEffects = true)

  def call(functionName: String, values: Map[String, Any]): Statement[Unit] = {
    val function = if (values.nonEmpty) {
      val body = values.map {
        case (key, value) => s"\t$key: ${JavaScriptContent.toJS(value)}"
      }.mkString(",\n")
      s"$functionName({\n$body\n});"
    } else {
      s"$functionName();"
    }
    call(function)
  }

  def options(functionName: String, values: Map[String, String], waitForResults: Boolean = true) = {
    values.foreach {
      case (key, value) => {    // TODO: see if there is a more efficient way to set multiple options at once
        option(functionName, key, value, waitForResults = waitForResults)
      }
    }
  }

  def option(functionName: String, key: String, value: Any, waitForResults: Boolean = true) = {
    val function = s"$functionName('option', '$key', ${JavaScriptContent.toJS(value)})"
    call(function)
  }

  def on(eventType: String, function: JavaScriptContent) = {
    call(s"on('$eventType', ${function.content})")
  }
}

class jQueryOffset(jqs: jQuerySelector) {
  lazy val left = ExistingStatement[Double](s"${jqs.content}.offset().left")
  lazy val top = ExistingStatement[Double](s"${jqs.content}.offset().top")
}

class jQueryPosition(jqs: jQuerySelector) {
  lazy val left = ExistingStatement[Double](s"${jqs.content}.position().left")
  lazy val top = ExistingStatement[Double](s"${jqs.content}.position().top")
}