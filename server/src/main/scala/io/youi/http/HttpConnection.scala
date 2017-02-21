package io.youi.http

import io.youi.server.Server
import io.youi.{MapStore, Store}

class HttpConnection(val server: Server, val request: HttpRequest) {
  private var responseVar: HttpResponse = HttpResponse()
  private var finished = false

  val store: Store = new MapStore()

  def response: HttpResponse = responseVar

  def update(f: HttpResponse => HttpResponse): Unit = synchronized {
    responseVar = f(responseVar)
  }

  def isWebSocketUpgradeRequest: Boolean = Headers.`Upgrade`.get(request.headers).contains("websocket") && Headers.`Connection`.get(request.headers).contains("Upgrade")
  def webSocketSupport: Option[Connection] = store.get[Connection](Connection.key)
  def webSocketSupport_=(listener: Connection): Unit = {
    if (isWebSocketUpgradeRequest) {
      store.update(Connection.key, listener)
      update { response =>
        response.copy(status = Status.SwitchingProtocols)
      }
    } else {
      throw new RuntimeException(s"Not a WebSocket upgrade request! Expected 'Upgrade' header set to 'websocket' and 'Connection' set to 'Upgrade'.")
    }
  }
  def proxySupport: Option[ProxyHandler] = store.get[ProxyHandler](ProxyHandler.key)
  def proxySupport_=(handler: ProxyHandler): Unit = {
    store.update(ProxyHandler.key, handler)
  }

  def isFinished: Boolean = finished
  def finish(): Unit = finished = true
}