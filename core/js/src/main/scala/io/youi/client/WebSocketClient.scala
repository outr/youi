package io.youi.client

import io.youi.http.WebSocket
import io.youi.net.URL
import org.scalajs.dom.{Blob, Event, FileReader, MessageEvent, WebSocket => WS}

import scala.concurrent.Future
import scribe.Execution.global

import scala.scalajs.js.typedarray._
import scala.scalajs.js.typedarray.TypedArrayBufferOps._

class WebSocketClient(url: URL) extends WebSocket {
  private lazy val webSocket: WS = new WS(url.toString)

  def connect(): Future[Unit] = {
    webSocket
    webSocket.addEventListener("open", (_: Event) => {
      updateStatus()
    })
    webSocket.addEventListener("close", (_: Event) => {
      updateStatus()
    })
    webSocket.addEventListener("error", (_: Event) => {
      error @= new RuntimeException("WebSocket error! Closing...")
      dispose()
      updateStatus()
    })
    webSocket.addEventListener("message", (evt: MessageEvent) => {
      evt.data match {
        case s: String => receive.text @= s
        case blob: Blob => {
          val fileReader = new FileReader
          fileReader.onload = (_: Event) => {
            val arrayBuffer = fileReader.result.asInstanceOf[ArrayBuffer]
            receive.binary @= TypedArrayBuffer.wrap(arrayBuffer)
          }
          fileReader.readAsArrayBuffer(blob)
        }
        case ab: ArrayBuffer => receive.binary @= TypedArrayBuffer.wrap(ab)
      }
    })
    connected.future(b => b).map(_ => ())
    send.text.attach(webSocket.send)
    send.binary.attach { message =>
      if (message.hasTypedArray()) {
        webSocket.send(message.typedArray().buffer)
      } else {
        val array = new Array[Byte](message.remaining())
        message.get(array)
        val arrayBuffer = array.toTypedArray.buffer
        webSocket.send(arrayBuffer)
      }
    }
    connected.future(_ == true).map(_ => ())
  }

  private def updateStatus(): Unit = webSocket.readyState match {
    case WS.CLOSED => _connected @= false
    case WS.CLOSING => _connected @= false
    case WS.CONNECTING => _connected @= false
    case WS.OPEN => _connected @= true
  }

  def disconnect(): Unit = {
    webSocket.close()
    _connected @= false
  }

  def dispose(): Unit = disconnect()
}