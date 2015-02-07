package org.hyperscala.jquery

import org.hyperscala.html.HTMLTag
import org.hyperscala.javascript.{JavaScriptContent, JavaScriptString}
import org.hyperscala.javascript.dsl.Statement
import org.hyperscala.selector.Selector
import org.hyperscala.web.WrappedComponent

import org.hyperscala.jquery.dsl._

/**
 * jQueryComponent trait works to provide easier access to making calls to jQuery for extensions like autoComplete and
 * other jQuery plugins.
 *
 * @author Matt Hicks <matt@outr.com>
 */
trait jQueryComponent extends WrappedComponent[HTMLTag] {
  lazy val tagSelector = $(wrapped)

  protected def functionName: String

  protected def realtimeSelector: Option[Selector] = Some(tagSelector.selector)

  private def send(statement: Statement[_]) = synchronized {
    if (initialized) {
      webpage.eval(statement, realtimeSelector.map(s => JavaScriptString(s"""$$('${s.content}').length > 0""")))
    } else {
      throw new RuntimeException("component not initialized!")
    }
  }

  protected def initializeComponent(values: Map[String, Any]) = {
    send(tagSelector.call(functionName, values))
  }

  protected def modify(key: String, value: Any) = {
    send(tagSelector.option(functionName, key, value))
  }

  def call() = if (initialized) {
    send(tagSelector.call(s"$functionName()"))
  }

  def call(function: String) = {
    send(tagSelector.call(s"$functionName('$function')"))
  }

  def call(function: String, arg: Any) = {
    val argValue = JavaScriptContent.toJS(arg)
    send(tagSelector.call(s"$functionName('$function', $argValue)"))
  }

  def on(eventType: String, function: JavaScriptContent) = {
    if (wrapped.rendered) {
      send(tagSelector.on(eventType, function))
    } else {
      option(eventType, function)
    }
  }
}
