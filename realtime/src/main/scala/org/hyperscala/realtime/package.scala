package org.hyperscala

import org.hyperscala.event.JavaScriptEvent
import org.hyperscala.event.processor.JavaScriptEventProcessor

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object realtime {
  implicit class RealtimeEventProcessor[T <: JavaScriptEvent](p: JavaScriptEventProcessor[T]) {
    def onRealtime(f: T => Unit) = {
      p := RealtimeEvent()
      p.on(f)
    }
  }
}
