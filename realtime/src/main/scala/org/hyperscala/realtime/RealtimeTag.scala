package org.hyperscala.realtime

import org.hyperscala.event.BrowserEvent
import org.hyperscala.html.HTMLTag
import org.hyperscala.javascript.dsl.{Statement, ExistingStatement}
import org.hyperscala.jquery.MappedEvent
import org.powerscala.Unique

/**
 * @author Matt Hicks <matt@outr.com>
 */
class RealtimeTag private[realtime](t: HTMLTag) {
  /**
   * Callback creates a Statement that can invoked on the client to asynchronously call the supplied function on the
   * server.
   *
   * @param mapping the mapping of resulting type
   * @param f the function to invoked when the statement is invoked
   * @tparam T the event type the function will receive
   * @return Statement[Unit]
   */
  def callback[T <: BrowserEvent](mapping: MappedEvent[T])(f: T => Unit): Statement[Unit] = {
    val js = s"realtime.send({ id: '${t.identity}', type: '${mapping.eventType}', ${mapping.mapping.map(t => s"${t._1}: ${t._2.content}").mkString(", ")} });"
    t.handle[T]((evt: T) => f(evt))(mapping.manifest)
    ExistingStatement[Unit](js, sideEffects = true)
  }

  def callback(f: => Unit): Statement[Unit] = {
    val id = Unique()
    val js = s"realtime.send({ id: '${t.identity}', type: 'tokenEvent', token: '$id'});"
    t.handle[TokenBrowserEvent]((evt: TokenBrowserEvent) => f, (evt: TokenBrowserEvent) => evt.token == id)
    ExistingStatement[Unit](js, sideEffects = true)
  }
}