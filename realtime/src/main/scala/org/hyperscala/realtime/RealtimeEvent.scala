package org.hyperscala.realtime

import org.hyperscala.javascript.JavaScriptContent

/**
 * RealtimeEvent is a configurable JavaScriptContent that can be supplied to any HTMLTag's JavaScript event to cause all
 * events of that type to propagate to the server.
 *
 * @author Matt Hicks <matt@outr.com>
 */
case class RealtimeEvent(confirmation: String = null,
                         preventDefault: Boolean = true,
                         fireChange: Boolean = false,
                         delay: Int = 0) extends JavaScriptContent {
  lazy val content = {
    val conf = JavaScriptContent.toJS(confirmation)
    s"return realtime.event(event, (typeof data === 'undefined') ? null : data, $conf, $preventDefault, $fireChange, $delay);"
  }
}
