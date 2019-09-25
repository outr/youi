package io.youi.http

import java.nio.ByteBuffer

import com.outr.hookup.HookupManager
import io.circe.Json
import io.circe.parser.parse
import reactify._
import io.youi.{MapStore, Store}
import reactify.reaction.Reaction

import scala.collection.mutable.ListBuffer

class Connection(client: Boolean) {
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
    val binaryReaction: Reaction[ByteBuffer] = Reaction { b: ByteBuffer =>
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
            case b: ByteBuffer => send.binary := b
          }
          backlog.clear()
        } else {
          send.text.reactions += textReaction
          send.binary.reactions += binaryReaction
        }
      }
    }
  }

  def hookup(): Unit = {
    val hookups = if (client) {
      HookupManager.clients
    } else {
      HookupManager(this, registerAllServers = true)
    }
    // Send output from Hookups
    val reaction = hookups.io.output.attach { json =>
      send.text := s"[HKP]${json.spaces2}"
    }
    store("applicationConnectivity") = reaction
    // Receive input from connection
    receive.text.attach { s =>
      if (s.startsWith("[HKP]")) {
        parse(s.substring(5)) match {
          case Left(pf) => throw pf
          case Right(json) => hookups.io.input := json
        }
      }
    }
  }

  def unHookup(): Unit = {
    val hookups = if (client) {
      HookupManager.clients
    } else {
      HookupManager(this)
    }
    val reaction = store[Reaction[Json]]("applicationConnectivity")
    hookups.io.output.reactions -= reaction
  }

  def close(): Unit = if (connected()) {
    send.close.set(())
    _connected := false
  }
}

class WebSocketChannels {
  val text: Channel[String] = Channel[String]
  val binary: Channel[ByteBuffer] = Channel[ByteBuffer]
  val close: Channel[Unit] = Channel[Unit]
}

object Connection {
  var debug: Boolean = false
  val key = "webSocketConnection"

  def backlog(listener: Connection, message: Any): Unit = listener.synchronized {
    listener.backlog += message
  }
}
