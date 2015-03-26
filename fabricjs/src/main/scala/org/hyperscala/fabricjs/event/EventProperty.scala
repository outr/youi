package org.hyperscala.fabricjs.event

import org.hyperscala.javascript.JavaScriptContent
import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
class EventProperty(name: String) extends Property[JavaScriptContent]() {
  /**
   * Concatenation of JavaScript support
   */
  def +=(content: JavaScriptContent) = if (value == null) {
    value = content
  } else {
    value = value + content
  }
}