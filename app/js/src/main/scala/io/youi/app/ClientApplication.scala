package io.youi.app

import com.outr.reactify.{ChangeListener, Val, Var}
import io.youi.app.screen.ScreenManager
import io.youi.app.stream.StreamURL
import io.youi.http.{Connection, WebSocketUtil}
import io.youi.net.URL
import org.scalajs.dom._

import com.outr.scribe._

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

trait ClientApplication extends YouIApplication with ScreenManager {
  val connection: Connection = new Connection(this)
  val webSocket: Var[Option[WebSocket]] = Var(None)

  activeConnections := Set(connection)

  if (autoConnect) {
    connect()
  }
  connection.connected.changes(new ChangeListener[Boolean] {
    override def change(oldValue: Boolean, newValue: Boolean): Unit = if (oldValue && !newValue && autoReload) {
      attemptReload()
    }
  })

  def autoConnect: Boolean = true

  def autoReload: Boolean = true

  def connect(): Unit = synchronized {
    disconnect()
    val url = URL(s"ws://${window.location.host}$connectionPath")
    webSocket := Some(WebSocketUtil.connect(url, connection))
  }

  def disconnect(): Unit = synchronized {
    webSocket().foreach { ws =>
      if (ws.readyState == WebSocket.OPEN) {
        ws.close()
      }
      webSocket := None
    }
  }

  def close(): Unit = {
    connection.close()
    disconnect()
  }

  private def attemptReload(attempt: Int = 0): Unit = {
    StreamURL.stream(History.url()).onComplete {
      case Success(html) => History.reload(force = true)
      case Failure(exception) => if (attempt < 100) {
        window.setTimeout(() => {
          attemptReload(attempt + 1)
        }, 5000)
      } else {
        logger.info("Unable to connect to server. Giving up!")
      }
    }
  }
}