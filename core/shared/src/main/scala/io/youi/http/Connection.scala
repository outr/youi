package io.youi.http

import java.nio.ByteBuffer

import reactify._
import io.youi.{MapStore, Store}

import scala.collection.mutable.ListBuffer

class Connection {
  private[youi] val _connected: Var[Boolean] = Var[Boolean](false)
  val connected: Val[Boolean] = Val(_connected)

  val send = new WebSocketChannels
  val receive = new WebSocketChannels
  val error: Channel[Throwable] = Channel[Throwable]
  val store: Store = new MapStore

  private val backlog = ListBuffer.empty[Any]

  init()

  // Set up backlog for sending until connection has been established
  private def init(): Unit = {
    val textListener: Listener[String] = (t: String) => {
      Connection.backlog(this, t)
    }
    val binaryListener: Listener[Array[ByteBuffer]] = (b: Array[ByteBuffer]) => {
      Connection.backlog(this, b)
    }
    send.text.observe(textListener)
    send.binary.observe(binaryListener)

    send.text.attach { s =>
      if (Connection.debug) scribe.info(s"Send: $s")
    }
    receive.text.attach { s =>
      if (Connection.debug) scribe.info(s"Receive: $s")
    }

    connected.attach { b =>
      synchronized {
        send.text.detach(textListener)
        send.binary.detach(binaryListener)
        if (b) {
          backlog.foreach {
            case s: String => send.text := s
            case b: Array[ByteBuffer] => send.binary := b
          }
          backlog.clear()
        } else {
          send.text.observe(textListener)
          send.binary.observe(binaryListener)
        }
      }
    }
  }

  def close(): Unit = if (connected()) {
    send.close := Unit
    _connected := false
  }
}

class WebSocketChannels {
  val text: Channel[String] = Channel[String]
  val binary: Channel[Array[ByteBuffer]] = Channel[Array[ByteBuffer]]
  val close: Channel[Unit] = Channel[Unit]
}

object Connection {
  var debug: Boolean = false
  val key = "webSocketConnection"

  def backlog(listener: Connection, message: Any): Unit = listener.synchronized {
    listener.backlog += message
  }
}
