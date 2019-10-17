package io.youi.app

import io.youi.client.WebSocketClient
import io.youi.communication.Connection
import io.youi.net.{Protocol, URL}

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

  val connectivity: Connectivity[C] = new ClientConnectivity[C](() => new WebSocketClient(communicationURL), connection)
}