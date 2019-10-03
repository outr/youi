package io.youi.client

import io.youi.http.{Connection, WebSocketUtil}
import io.youi.net.URL
import org.scalajs.dom.WebSocket

import scala.concurrent.Future
import scribe.Execution.global

class WebSocketClient(url: URL) extends Connection(client = true) {
  private lazy val webSocket: WebSocket = WebSocketUtil.connect(url, this)

  def connect(): Future[Unit] = {
    webSocket
    connected.future(b => b).map(_ => ())
  }

  def disconnect(): Unit = {
    webSocket.close()
    _connected @= false
  }

  def dispose(): Unit = disconnect()
}