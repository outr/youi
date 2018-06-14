package io.youi.http

import io.youi.server.Server
import io.youi.stream.Delta
import io.youi.{MapStore, Store}

class HttpConnection(val server: Server, val request: HttpRequest) {
  private var responseVar: HttpResponse = HttpResponse()
  private var finished = false

  val store: Store = new MapStore()

  def response: HttpResponse = responseVar

  def update(f: HttpResponse => HttpResponse): Unit = synchronized {
    responseVar = f(responseVar)
  }

  def isWebSocketUpgradeRequest: Boolean = Headers.`Connection`.all(request.headers).contains("Upgrade")
  def webSocketSupport: Option[Connection] = store.get[Connection](Connection.key)
  def webSocketSupport_=(listener: Connection): Unit = {
    if (isWebSocketUpgradeRequest) {
      store.update(Connection.key, listener)
      update { response =>
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

  def isFinished: Boolean = finished
  def finish(): Unit = finished = true
}