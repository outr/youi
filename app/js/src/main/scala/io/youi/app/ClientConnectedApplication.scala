package io.youi.app

import io.youi.client.{BlobData, WebSocketClient}
import io.youi.communication.Connection
import io.youi.http.ConnectionStatus
import io.youi.net.{Protocol, URL}
import io.youi.util.Time
import io.youi.{BackgroundUpdates, History}
import org.scalajs.dom._
import scribe.Execution.global

import scala.concurrent.Future
import scala.concurrent.duration._

trait ClientConnectedApplication[C <: Connection] extends ClientApplication with YouIConnectedApplication[C] {
  def communicationURL: Future[URL] = baseURL.map { url =>
    val protocol = if (url.protocol == Protocol.Https) {
      Protocol.Wss
    } else {
      Protocol.Ws
    }
    url
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
    connection.status.attach {
      case ConnectionStatus.Open => connection.webSocket.foreach(_.send.text @= "PING")
      case ConnectionStatus.Closed => disconnected()
      case _ => // Ignore others
    }
    window.addEventListener("online", (_: Event) => {
      scribe.info("Back online, reconnecting...")
      connection.disconnect()
    })
    BackgroundUpdates.delta.attach { d =>
      if (d >= 60.0) {
        scribe.info(s"Resumed from sleeping, reconnecting...")
        connection.disconnect()
      }
    }
    connectCommunication match {
      case ConnectCommunication.AutoConnectSynchronous => connect().map(_ => ())
      case ConnectCommunication.AutoConnectAsynchronous => {
        connect()
        Future.successful(())
      }
      case ConnectCommunication.ManualConnect => Future.successful(())
    }
  }

  def connect(): Future[ConnectionStatus] = communicationURL.flatMap { url =>
    val ws = new WebSocketClient(url)
    connection.webSocket @= Some(ws)
    ws.connect()
  }

  def reconnected(): Future[Unit] = Future.successful(())

  def upload(file: File): Future[String] = {
    val webSocket = connection.webSocket().getOrElse(throw new RuntimeException("Not connected!"))
    val fileName = file.name
    val bytes = file.size.toLong
    val upload = connection.upload(fileName, bytes)
    webSocket.send.binary @= BlobData(file)
    upload.progress.attach { p =>
      scribe.info(s"Progress: $p")
    }
    upload.percentage.attach { p =>
      scribe.info(s"Percentage: $p")
    }
    upload.remaining.attach { r =>
      scribe.info(s"Remaining: $r")
    }
    upload.future
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
    case ReconnectStrategy.Reconnect => Time
      .delay(reconnectDelay)
      .flatMap(_ => connect())
      .flatMap(_ => reconnected())
    case ReconnectStrategy.Stop => ()
  }

  def dispose(): Unit = {
    connectionMonitor.stop()
  }
}