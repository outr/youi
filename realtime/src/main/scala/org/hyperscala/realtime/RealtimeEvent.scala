package org.hyperscala.realtime

import org.hyperscala.javascript.JavaScriptContent
import org.powerscala.concurrent.Time

/**
 * RealtimeEvent is a configurable JavaScriptContent that can be supplied to any HTMLTag's JavaScript event to cause all
 * events of that type to propagate to the server.
 *
 * @param confirmation optionally confirmation message that should be shown before sending the event
 * @param preventDefault true if the default event should be prevented (defaults to true)
 * @param fireChange if true a change event is fired immediately before this event (defaults to false)
 * @param delay the amount of time to delay before sending the event in seconds (defaults to 0)
 * @param maximumRate the maximum rate to send events in seconds. For example, if 5 seconds is specified then an event
 *                    will be sent to the server a maximum of every five seconds.
 *
 * @author Matt Hicks <matt@outr.com>
 */
case class RealtimeEvent(confirmation: Option[String] = None,
                         preventDefault: Boolean = true,
                         fireChange: Boolean = false,
                         delay: Double = 0.0,
                         maximumRate: Double = 0.0) extends JavaScriptContent {
  lazy val content = {
    val conf = confirmation.map(JavaScriptContent.toJS).orNull
    val d = Time.millis(delay)
    val maxRate = Time.millis(maximumRate)
    s"return realtime.event(event, $conf, $preventDefault, $fireChange, $d, $maxRate);"
  }
}
