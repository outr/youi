package io.youi.communication

import io.circe.Json
import reactify.{Val, Var}

import scala.concurrent.Future
import scala.language.experimental.macros

trait Connection {
  protected val _status: Var[ConnectionStatus] = Var(ConnectionStatus.Disconnected)

  val queue: HookupQueue = new HookupQueue
  val status: Val[ConnectionStatus] = _status

  object hookups {
    private var map = Map.empty[String, Hookup[Any]]

    def register[Interface](hookup: Hookup[Interface]): Unit = synchronized {
      map += hookup.name -> hookup.asInstanceOf[Hookup[Any]]
    }

    def byName(name: String): Hookup[Any] = map.getOrElse(name, throw new RuntimeException(s"Unable to find hookup by name: $name (names: ${map.keySet.mkString(", ")})"))
  }

  def receive(json: Json): Future[Json] = {
    val endPoint = (json \\ "endpoint").head.asString.getOrElse(throw new RuntimeException(s"No 'method' entry defined for: $json"))
    val name = endPoint.substring(0, endPoint.indexOf('.'))
    val hookup = hookups.byName(name)
    hookup.receive(json)
  }

  protected def interface[Interface]: Interface with Hookup[Interface] = macro HookupMacros.interface[Interface]
  protected def implementation[Interface, Implementation <: Interface]: Implementation with Hookup[Interface] = macro HookupMacros.implementation[Interface, Implementation]
}