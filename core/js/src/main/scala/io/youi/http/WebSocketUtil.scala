package io.youi.http


import io.youi.net.URL
import org.scalajs.dom._

import scala.scalajs.js.typedarray._
import scala.scalajs.js.typedarray.TypedArrayBufferOps._

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
    listener.send.binary.attach { message =>
      if (ws.readyState >= WebSocket.CLOSING) {
        listener.close()
        Connection.backlog(listener, message)
      } else if (ws.readyState == WebSocket.OPEN) {
        if (message.hasTypedArray()) {
          ws.send(message.typedArray().buffer)
        } else {
          val array = new Array[Byte](message.remaining())
          message.get(array)
          val arrayBuffer = array.toTypedArray.buffer
          ws.send(arrayBuffer)
        }
      } else {
        Connection.backlog(listener, message)
      }
    }
    ws.onopen = (_: Event) => {
      listener._connected @= true
    }
    ws.onerror = (_: Event) => {
      listener.error @= new RuntimeException("WebSocket error!")
      listener.close()
    }
    ws.onclose = (_: CloseEvent) => {
      listener.close()
    }
    ws.onmessage = (evt: MessageEvent) => {
      evt.data match {
        case s: String => listener.receive.text @= s
        case blob: Blob => {
          val fileReader = new FileReader
          fileReader.onload = (_: Event) => {
            val arrayBuffer = fileReader.result.asInstanceOf[ArrayBuffer]
            listener.receive.binary @= TypedArrayBuffer.wrap(arrayBuffer)
          }
          fileReader.readAsArrayBuffer(blob)
        }
        case ab: ArrayBuffer => listener.receive.binary @= TypedArrayBuffer.wrap(ab)
      }
    }
    ws
  }
}
