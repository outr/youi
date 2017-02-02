package io.youi.app

import com.outr.reactify.{Val, Var}
import io.youi.app.screen.ScreenManager
import io.youi.http.{Connection, WebSocketUtil}
import io.youi.net.URL
import org.scalajs.dom._

trait ClientApplication extends YouIApplication with ScreenManager {
  override val connection: Connection = new Connection(this)
  val webSocket: Var[Option[WebSocket]] = Var(None)

  activeConnections := Set(connection)

  if (autoConnect) {
    connect()
  }

  def autoConnect: Boolean = true

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
}