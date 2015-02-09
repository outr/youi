package org.hyperscala

import com.outr.net.http.session.Session
import org.hyperscala.event.JavaScriptEvent
import org.hyperscala.event.processor.JavaScriptEventProcessor
import org.hyperscala.html.HTMLTag
import org.hyperscala.web.Webpage
import org.powerscala.Storage

import scala.language.implicitConversions

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

  implicit def realtimePage[S <: Session](webpage: Webpage[S]): RealtimePage[S] = RealtimePage(webpage)

  implicit def realtimeTag[Tag <: HTMLTag](tag: Tag): RealtimeTag = Storage.getOrSet(tag, "realtimeTag", new RealtimeTag(tag))
}