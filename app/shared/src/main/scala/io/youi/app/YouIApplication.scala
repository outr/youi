package io.youi.app

import java.util.concurrent.atomic.AtomicInteger

import com.outr.reactify._
import io.youi.communication.Communication
import io.youi.http.Connection
import com.outr.scribe._

import scala.language.experimental.macros

/**
  * Base trait to define shared client and server information. This trait must be extended in the shared code as a trait
  * and implemented in both client and server implementations.
  */
trait YouIApplication {
  private[app] val activeConnections = Var[Set[Connection]](Set.empty)

  /**
    * Listing of all active connections. On the client this will only every have one entry.
    */
  val connections: Val[Set[Connection]] = Val(activeConnections)

  /**
    * The absolute path to establish / listen for web socket communication.
    *
    * Defaults to "/communication".
    */
  def connectionPath: String = "/communication"

  /**
    * Current Connection. On the server this will be defined as part of ThreadLocal. On the client this will represent
    * a singleton.
    */
  def connection: Connection

  def withConnection[R](connection: Connection)(f: => R): R = f

  def communication[C <: Communication]: CommunicationManager[C] = macro Macros.communication[C]
}

class CommunicationManager[C <: Communication](application: YouIApplication, create: Connection => C) {
  val id: Int = CommunicationManager.increment.getAndIncrement()

  val instances: Val[Set[C]] = Val {
    application.connections.map { connection =>
      connection.store.getOrSet(s"communicationManager$id", create(connection))
    }
  }

  def current: C = instances.find(_.connection eq application.connection).get

  def apply(): C = current
}

object CommunicationManager {
  private val increment = new AtomicInteger(0)
}