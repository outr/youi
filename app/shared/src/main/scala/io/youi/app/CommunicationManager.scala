package io.youi.app

import java.util.concurrent.atomic.AtomicInteger

import reactify.Val
import io.youi.communication.Communication
import io.youi.http.Connection

class CommunicationManager[C <: Communication](appComm: ApplicationCommunication, create: Connection => C) {
  val id: Int = CommunicationManager.increment.getAndIncrement()

  val instances: Val[Set[C]] = Val {
    appComm.connections.map { connection =>
      connection.store.getOrSet(s"communicationManager$id", create(connection))
    }
  }

  def byConnection(connection: Connection): Option[C] = instances.find(_.connection eq connection)

  def apply(connection: Connection): C = byConnection(connection).getOrElse(throw new RuntimeException("No CommunicationManager found for connection!"))
}

object CommunicationManager {
  private val increment = new AtomicInteger(0)
}