package org.hyperscala.realtime.dsl

import org.hyperscala.javascript.dsl.Statement
import org.hyperscala.realtime.Realtime

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class RealtimeStatement(statement: Statement) {
  def send() = Realtime.send(statement)
}
