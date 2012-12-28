package org.hyperscala.javascript

import org.hyperscala.PropertyAttribute

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait EventProperty extends PropertyAttribute[JavaScriptContent] {
  /**
   * Concatenation of JavaScript support
   */
  def +=(content: JavaScriptContent) = if (value == null) {
    value = content
  } else {
    value = value + content
  }
}