package org.hyperscala

import org.hyperscala.event.JavaScriptEvent
import org.hyperscala.event.processor.JavaScriptEventProcessor
import org.hyperscala.html.HTMLTag
import org.hyperscala.javascript.dsl.Statement
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

  implicit class RealtimeStatement[T](statement: Statement[T]) {
    def send(webpage: Webpage) = webpage.eval(statement)
    def send(t: HTMLTag) = t.connected[Webpage] {
      case webpage => webpage.eval(statement)
    }
  }

  implicit def realtimePage(webpage: Webpage): RealtimePage = RealtimePage(webpage)

  implicit def realtimeTag[Tag <: HTMLTag](tag: Tag): RealtimeTag[Tag] = Storage.getOrSet[String, RealtimeTag[Tag]](tag, "realtimeTag", new RealtimeTag[Tag](tag))
}