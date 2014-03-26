package org.hyperscala.realtime.dsl

import org.hyperscala.javascript.dsl.Statement
import org.hyperscala.realtime.Realtime
import com.outr.net.http.session.Session
import org.hyperscala.web.Webpage

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class RealtimeStatement[T](statement: Statement[T]) {
  def send[S <: Session](webpage: Webpage[S]) = Realtime.send(webpage, statement)
}
