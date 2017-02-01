package io.youi.app

import java.util.concurrent.atomic.AtomicInteger

import com.outr.reactify.Val
import io.youi.communication.Communication
import io.youi.http.Connection

class CommunicationManager[C <: Communication](application: YouIApplication, create: Connection => C) {
  val id: Int = CommunicationManager.increment.getAndIncrement()

  val instances: Val[Set[C]] = Val {
    application.connections.map { connection =>
      connection.store.getOrSet(s"communicationManager$id", create(connection))
    }
  }

  def byConnection(connection: Connection): Option[C] = instances.find(_.connection eq connection)

  def current: C = byConnection(application.connection).get

  def apply(): C = current
}

object CommunicationManager {
  private val increment = new AtomicInteger(0)
}