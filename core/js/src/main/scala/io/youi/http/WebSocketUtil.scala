package io.youi.http

import com.outr.scribe.Logging
import io.youi.net.URL
import org.scalajs.dom.{CloseEvent, ErrorEvent, Event, MessageEvent, WebSocket}

object WebSocketUtil extends Logging {
  def connect(socketURL: URL, listener: WebSocketListener): WebSocket = {
    val ws = new WebSocket(socketURL.toString)
    listener.send.text.attach { message =>
      ws.send(message)
    }
    ws.onopen = (evt: Event) => {
      listener._connected := true
    }
    ws.onerror = (evt: ErrorEvent) => {
      listener.error := new RuntimeException(s"${evt.filename}:${evt.lineno}:${evt.colno} - ${evt.message}")
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
