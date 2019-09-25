package io.youi.app

import io.youi.http.Connection
import reactify.{Val, Var}

import scala.language.experimental.macros

/**
  * ApplicationCommunication defines a communication end-point for Communication instances. More than one can be defined
  * in an application.
  *
  * @param application the application this is associated with
  * @param path the absolute path to establish or listen for web socket communication. Defaults to "/communication"
  */
class ApplicationConnectivity private[app](val application: YouIApplication,
                                           val path: String,
                                           val autoConnect: Boolean) {
  private[app] val activeConnections = Var[Set[Connection]](Set.empty)

  /**
    * Listing of all active connections. On the client this will only ever have one entry.
    */
  val connections: Val[Set[Connection]] = Val(activeConnections)

  // Make sure the application knows about it
  application.synchronized {
    val entries = application.connectivityEntries()
    application.connectivityEntries.asInstanceOf[Var[Set[ApplicationConnectivity]]] := entries + this
  }

  connections.changes((before, after) => {
    val added = after -- before
    val removed = before -- after
    added.foreach { connection =>
      connection.hookup()
    }
    removed.foreach { connection =>
      connection.unHookup()
    }
  })
}