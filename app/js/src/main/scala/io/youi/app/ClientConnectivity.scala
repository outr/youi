package io.youi.app

import io.youi.communication.Connection
import io.youi.http.WebSocket

import scala.concurrent.Future

class ClientConnectivity[C <: Connection](creator: () => WebSocket, connection: C) extends Connectivity[C](connection) {
  def connect(): Future[Unit] = {
    disconnect()

    val ws = creator()
    webSocket := Some(ws)
    ws.connect()
  }
}