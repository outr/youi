package org.hyperscala

import org.hyperscala.event.processor.JavaScriptEventProcessor
import org.hyperscala.event.JavaScriptEvent
import org.hyperscala.realtime.RealtimeEvent

import scala.language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object realtime {
  implicit def jsEventProcessor2RealtimeEventProcessor[T <: JavaScriptEvent](p: JavaScriptEventProcessor[T]) = {
    new RealtimeEventProcessor(p)
  }
}

class RealtimeEventProcessor[T <: JavaScriptEvent](p: JavaScriptEventProcessor[T]) {
  def onRealtime(f: T => Unit) = {
    p := RealtimeEvent()
    p.on(f)
  }
}