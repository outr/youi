package io.youi.app

import com.outr.hookup.HookupManager
import io.circe.Json
import io.youi.http.Connection
import reactify.{Val, Var}
import io.circe.parser._
import reactify.reaction.Reaction

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

  connections.changes((before, after) => {
    val added = after -- before
    val removed = before -- after
    added.foreach { connection =>
      val hookups = if (application.isClient) {
        HookupManager.clients
      } else {
        HookupManager(connection)
      }
      // Send output from Hookups
      val reaction = hookups.io.output.attach { json =>
        connection.send.text := json.spaces2
      }
      connection.store("applicationConnectivity") = reaction
      // Receive input from connection
      connection.receive.text.attach { s =>
        parse(s).foreach { json =>
          hookups.io.input := json
        }
      }
    }
    removed.foreach { connection =>
      val hookups = if (application.isClient) {
        HookupManager.clients
      } else {
        HookupManager(connection)
      }
      val reaction = connection.store[Reaction[Json]]("applicationConnectivity")
      hookups.io.output.reactions -= reaction
    }
  })
}