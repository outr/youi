package org.hyperscala.realtime

import org.hyperscala.javascript.JavaScriptContent

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class RealtimeEvent(confirmation: String = null,
                         preventDefault: Boolean = true,
                         fireChange: Boolean = false,
                         onlyLast: Boolean = true,
                         delay: Int = 0) extends JavaScriptContent {
  lazy val content = {
    val conf = JavaScriptContent.toJS(confirmation)
    s"return realtimeEvent(event, (typeof data === 'undefined') ? null : data, $conf, $preventDefault, $fireChange, $onlyLast, $delay);"
  }
}