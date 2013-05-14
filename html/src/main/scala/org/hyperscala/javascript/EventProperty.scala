package org.hyperscala.javascript

import org.hyperscala.{Markup, PropertyAttribute}

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class EventProperty(name: String)(implicit markup: Markup) extends PropertyAttribute[JavaScriptContent](name, null) {
  /**
   * Concatenation of JavaScript support
   */
  def +=(content: JavaScriptContent) = if (value == null) {
    value = content
  } else {
    value = value + content
  }
}