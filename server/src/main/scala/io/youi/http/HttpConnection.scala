package io.youi.http

import io.youi.{MapStore, Store}

class HttpConnection(val request: HttpRequest) {
  private var _response: HttpResponse = HttpResponse()

  val store: Store = new MapStore()

  def response: HttpResponse = _response

  def update(f: HttpResponse => HttpResponse): Unit = synchronized {
    _response = f(_response)
  }

  def isWebSocketUpgradeRequest: Boolean = Headers.`Upgrade`.get(request.headers).contains("websocket") && Headers.`Connection`.get(request.headers).contains("Upgrade")
  def webSocketSupport: Option[WebSocketListener] = store.get(WebSocketListener.key)
  def webSocketSupport_=(listener: WebSocketListener) = {
    if (isWebSocketUpgradeRequest) {
      store.update(WebSocketListener.key, listener)
      update { response =>
        response.copy(status = Status.SwitchingProtocols)
      }
    } else {
      throw new RuntimeException(s"Not a WebSocket upgrade request!")
    }
  }
}