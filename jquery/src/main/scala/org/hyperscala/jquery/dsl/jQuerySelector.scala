package org.hyperscala.jquery.dsl

import org.hyperscala.selector.Selector
import org.hyperscala.javascript.dsl.TypedStatement
import org.hyperscala.css.Style
import org.hyperscala.javascript.{JSFunction1, JavaScriptContent}
import org.hyperscala.event.KeyboardEvent

/**
 * @author Matt Hicks <matt@outr.com>
 */
class jQuerySelector(val selector: Selector) extends TypedStatement[Selector] {
  def content = s"$$(${selector.content})"

  def scrollTop(offset: Int) = call(s"scrollTop($offset)")

  def blur() = call("blur()")
  def change() = call("change()")
  def focus() = call("focus()")
  def select() = call("select()")
  def submit() = call("submit()")

  def width() = TypedStatement[Double](s"$content.width()")
  def height() = TypedStatement[Double](s"$content.height()")

  def keyDown(f: JSFunction1[KeyboardEvent, Boolean]) = {
    TypedStatement[Unit](s"$content.keydown(${f.content})")
  }

  def keyPress(f: JSFunction1[KeyboardEvent, Boolean]) = {
    TypedStatement[Unit](s"$content.keypress(${f.content})")
  }

  def keyUp(f: JSFunction1[KeyboardEvent, Boolean]) = {
    TypedStatement[Unit](s"$content.keyup(${f.content})")
  }

  def value[T]() = TypedStatement[T](s"$content.val()")
  def value[T](s: TypedStatement[T]) = TypedStatement[T](s"$content.val(${s.content}})")

  def offset() = new jQueryOffset(this)
  def position() = new jQueryPosition(this)

  def css[S](style: Style[S]) = TypedStatement(s"$content.css('${style.cssName}')")

  def call(function: String): TypedStatement[Unit] = TypedStatement[Unit](s"$content.$function")

  def call(functionName: String, values: Map[String, Any]): TypedStatement[Unit] = {
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
  lazy val left = TypedStatement[Double](s"${jqs.content}.offset().left")
  lazy val top = TypedStatement[Double](s"${jqs.content}.offset().top")
}

class jQueryPosition(jqs: jQuerySelector) {
  lazy val left = TypedStatement[Double](s"${jqs.content}.position().left")
  lazy val top = TypedStatement[Double](s"${jqs.content}.position().top")
}