package io.youi.http

import java.nio.ByteBuffer

import reactify._
import io.youi.{MapStore, Store}
import reactify.reaction.Reaction

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
    val textReaction: Reaction[String] = Reaction { t: String =>
      Connection.backlog(this, t)
    }
    val binaryReaction: Reaction[Array[ByteBuffer]] = Reaction { b: Array[ByteBuffer] =>
      Connection.backlog(this, b)
    }
    send.text.reactions += textReaction
    send.binary.reactions += binaryReaction

    send.text.attach { s =>
      if (Connection.debug) scribe.info(s"Send: $s")
    }
    receive.text.attach { s =>
      if (Connection.debug) scribe.info(s"Receive: $s")
    }
    send.close.on {
      _connected := false
    }
    receive.close.on {
      _connected := false
    }

    connected.attach { b =>
      synchronized {
        send.text.reactions -= textReaction
        send.binary.reactions -= binaryReaction
        if (b) {
          backlog.foreach {
            case s: String => send.text := s
            case b: Array[ByteBuffer] => send.binary := b
          }
          backlog.clear()
        } else {
          send.text.reactions += textReaction
          send.binary.reactions += binaryReaction
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
