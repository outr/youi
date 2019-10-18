package io.youi.app

import io.youi.History
import io.youi.client.WebSocketClient
import io.youi.communication.Connection
import io.youi.http.ConnectionStatus
import io.youi.net.{Protocol, URL}
import io.youi.util.Time

import scala.concurrent.Future
import scribe.Execution.global

import scala.concurrent.duration._

trait ClientConnectedApplication[C <: Connection] extends ClientApplication with YouIConnectedApplication[C] {
  def communicationURL: URL = {
    val protocol = if (baseURL.protocol == Protocol.Https) {
      Protocol.Wss
    } else {
      Protocol.Ws
    }
    baseURL
      .withProtocol(protocol)
      .withPath(communicationPath)
  }

  def connection: C

  def reconnectDelay: FiniteDuration = 1.second
  def reconnectStrategy: ReconnectStrategy = ReconnectStrategy.Reload

  private val connectionMonitor = Time.repeat(
    delay = 30.seconds,
    stopOnError = false,
    errorHandler = error
  )(updateConnection())

  override protected def init(): Future[Unit] = super.init().flatMap { _ =>
    connection.status.attach { status =>
      if (status == ConnectionStatus.Closed) {
        disconnected()
      }
    }
    if (autoConnectCommunication) {
      connect().map(_ => ())
    } else {
      Future.successful(())
    }
  }

  def connect(): Future[ConnectionStatus] = {
    val ws = new WebSocketClient(communicationURL)
    connection.webSocket @= Some(ws)
    ws.connect()
  }

  private def updateConnection(): Future[Unit] = {
    val lastCommunication = System.currentTimeMillis() - connection.lastActive
    if (connection.status() == ConnectionStatus.Open && lastCommunication > 30.seconds.toMillis) {
      connection.webSocket.foreach(_.send.text @= "PING")
    }
    Future.successful(())
  }

  protected def disconnected(): Unit = reconnectStrategy match {
    case ReconnectStrategy.Reload => Time.delay(reconnectDelay).flatMap { _ =>
      connect().map {
        case ConnectionStatus.Open => Time.delay(1.second).map(_ => History.reload(force = true))
        case _ => // Ignore others
      }
    }
    case ReconnectStrategy.Reconnect => Time.delay(reconnectDelay).flatMap(_ => connect()).map(_ => ())
    case ReconnectStrategy.Stop => ()
  }

  def dispose(): Unit = {
    connectionMonitor.stop()
  }
}