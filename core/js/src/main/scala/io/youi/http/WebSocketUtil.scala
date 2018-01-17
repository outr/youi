package io.youi.http

import io.youi.net.URL
import org.scalajs.dom.{CloseEvent, ErrorEvent, Event, MessageEvent, WebSocket}

object WebSocketUtil {
  // TODO: revisit disconnecting, reconnecting, and errors
  def connect(socketURL: URL, listener: Connection): WebSocket = {
    val ws = new WebSocket(socketURL.toString)
    listener.send.text.attach { message =>
      if (ws.readyState >= WebSocket.CLOSING) {
        listener.close()
        Connection.backlog(listener, message)
      } else if (ws.readyState == WebSocket.OPEN) {
        ws.send(message)
      } else {
        Connection.backlog(listener, message)
      }
    }
    ws.onopen = (evt: Event) => {
      listener._connected := true
    }
    ws.onerror = (evt: Event) => {
      listener.error := new RuntimeException("WebSocket error!")
      listener.close()
    }
    ws.onclose = (evt: CloseEvent) => {
      listener.close()
    }
    ws.onmessage = (evt: MessageEvent) => {
      val message = evt.data.toString
      listener.receive.text := message
    }
    ws
  }
}
