package io.youi.app

import java.nio.ByteBuffer

import io.youi.client.WebSocketClient
import io.youi.{AnimationFrame, History}
import io.youi.net.URL
import io.youi.stream.StreamURL
import io.youi.util.Time
import org.scalajs.dom.{WebSocket, window}
import org.scalajs.dom.ext.AjaxException
import reactify._
import reactify.reaction.Reaction

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class ClientConnectivity(connectivity: ApplicationConnectivity, application: ClientApplication) {
  val webSocket: Var[Option[WebSocketClient]] = Var(None)

  private val receiveText: Reaction[String] = Reaction[String] {
    case "PING" => webSocket.foreach(_.send.text @= "PONG")
    case message if message.startsWith("[youi id=") => {
      val splitPoint = message.indexOf(']')
      val prefix = message.substring(0, splitPoint + 1) match {
        case ClientConnectivity.PrefixRegex(id) => id.toLong
      }
      application.connection.re
    }
    case message => scribe.warn(s"Unhandled: $message")
  }

  private val receiveBinary: Reaction[ByteBuffer] = Reaction[ByteBuffer] { message =>

  }

  webSocket.changes {
    case (oldClient, newClient) => {
      oldClient.foreach { c =>
        c.receive.text.reactions -= receiveText
        c.receive.binary.reactions -= receiveBinary

        c.dispose()
      }
      newClient.foreach { c =>
        c.receive.text.reactions += receiveText
        c.receive.binary.reactions += receiveBinary
      }
    }
  }

  connection.receive.text.attach {
    case "PING" => connection.send.text @= "PONG"
    case _ => // Ignore
  }

  if (connectivity.autoConnect) {
    connect()
  }

  connection.connected.changes {
    case (oldValue, newValue) => if (oldValue && !newValue && application.reconnectStrategy != ReconnectStrategy.Stop) {
      attemptReload()
    }
  }

  def connect(): Unit = {
    connectivity.activeConnections @= Set(connection)

    disconnect()
    webSocket @= Some(WebSocketUtil.connect(application.communicationURL, connection))
  }

  def disconnect(): Unit = synchronized {
    webSocket().foreach { ws =>
      if (ws.readyState == WebSocket.OPEN) {
        ws.close()
      }
      webSocket @= None
    }
  }

  def close(): Unit = {
    connection.close()
    disconnect()
  }

  private def attemptReload(attempt: Int = 0): Unit = {
    StreamURL.stream(History.url()).onComplete {
      case Success(_) => if (application.reconnectStrategy == ReconnectStrategy.Reload) {
        History.reload(force = true)
      } else {
        Time.delay(5.seconds).map { _ =>
          scribe.info("Attempting reconnect...")
          application.reConnect()
        }
      }
      case Failure(exception) => {
        exception match {
          case exc: AjaxException if exc.xhr.status > 0 => History.reload(force = true)
          case _ => {
            val timeout = if (attempt < 10) {
              2500
            } else if (attempt < 25) {
              5000
            } else if (attempt < 100) {
              10000
            } else {
              30000
            }

            window.setTimeout(() => {
              attemptReload(attempt + 1)
            }, timeout)
          }
        }
      }
    }
  }
}

object ClientConnectivity {
  private val PrefixRegex = """\[youi id="(\d+)"\]""".r
}