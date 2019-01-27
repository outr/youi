package io.youi.http

import io.youi.server.Server
import io.youi.stream.Delta
import io.youi.{MapStore, Store}

case class HttpConnection(server: Server,
                          request: HttpRequest,
                          response: HttpResponse = HttpResponse(),
                          finished: Boolean = false,
                          store: Store = new MapStore()) {
  def modify(f: HttpResponse => HttpResponse): HttpConnection = {
    copy(response = f(response))
  }

  def isWebSocketUpgradeRequest: Boolean = Headers.`Connection`.all(request.headers).contains("Upgrade")
  def webSocketSupport: Option[Connection] = store.get[Connection](Connection.key)
  def withWebSocket(listener: Connection): HttpConnection = {
    if (isWebSocketUpgradeRequest) {
      store.update(Connection.key, listener)
      modify { response =>
        response.copy(status = HttpStatus.SwitchingProtocols)
      }
    } else {
      throw new RuntimeException(s"Not a WebSocket upgrade request! Expected 'Connection' set to 'Upgrade'. Headers: ${request.headers}")
    }
  }

  object deltas {
    private val key: String = "deltas"

    def apply(): List[Delta] = store.getOrElse[List[Delta]](key, Nil)
    def clear(): Unit = store.remove(key)

    def +=(deltas: List[Delta]): Unit = store(key) = apply() ::: deltas
    def +=(delta: Delta): Unit = this += List(delta)

    def isEmpty: Boolean = apply().isEmpty
    def nonEmpty: Boolean = apply().nonEmpty
  }

  def finish(): HttpConnection = copy(finished = true)
}