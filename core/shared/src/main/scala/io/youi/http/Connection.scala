package io.youi.http

import java.nio.ByteBuffer

import com.outr.reactify._
import com.outr.scribe._
import io.youi.{MapStore, Store}

import scala.collection.mutable.ListBuffer

class Connection(val manager: ConnectionManager) {
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
    val textListener: String => Unit = (t: String) => {
      Connection.backlog(this, t)
    }
    val binaryListener: Array[ByteBuffer] => Unit = (b: Array[ByteBuffer]) => {
      Connection.backlog(this, b)
    }
    send.text.attach(textListener)
    send.binary.attach(binaryListener)

    send.text.attach { s =>
      if (Connection.debug) logger.info(s"Send: $s")
    }
    receive.text.attach { s =>
      if (Connection.debug) logger.info(s"Receive: $s")
    }

    connected.distinct.attach { b =>
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
          send.text.attach(textListener)
          send.binary.attach(binaryListener)
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
