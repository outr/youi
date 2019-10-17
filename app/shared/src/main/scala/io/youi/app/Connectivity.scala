package io.youi.app

import java.nio.ByteBuffer

import io.circe.Json
import io.circe.parser.parse
import io.youi.communication.{Connection, MessageType}
import io.youi.http.{ConnectionStatus, WebSocket}
import reactify.Var
import reactify.reaction.Reaction

import scala.util.{Failure, Success}
import scribe.Execution.global

class Connectivity[C <: Connection](val connection: C) {
  val webSocket: Var[Option[WebSocket]] = Var(None)

  private val receiveText: Reaction[String] = Reaction[String] {
    case "PING" => webSocket.foreach(_.send.text @= "PONG")
    case message if message.startsWith("[youi id=") => {
      val splitPoint = message.indexOf(']')
      val (id, typ) = message.substring(0, splitPoint + 1) match {
        case Connectivity.PrefixRegex(i, t) => (i.toLong, t)
      }
      val messageType = MessageType.byName(typ)
      val content = message.substring(splitPoint + 1)
      val json = parse(content).getOrElse(throw new RuntimeException(s"Unable to parse JSON for $id ($typ): $content"))
      messageType match {
        case MessageType.Invoke => connection.receive(json).onComplete {
          case Success(response) => connection.queue.enqueue(MessageType.Response, response, id)
          case Failure(throwable) => {
            scribe.error(s"Error while processing method request: $json", throwable)
            connection.queue.enqueue(MessageType.Error, Json.fromString(throwable.getMessage), id)
          }
        }
        case MessageType.Response => if (connection.queue.success(id, json)) {
          // Success
        } else {
          scribe.warn(s"No id found for $id. Cannot apply: $json")
        }
        case MessageType.Error => if (connection.queue.failure(id, new RuntimeException(json.asString.getOrElse(json.toString())))) {
          // Success
        } else {
          scribe.warn(s"No id found for $id. Cannot fail: $json")
        }
      }
    }
    case message => scribe.warn(s"Unhandled: $message")
  }

  private val receiveBinary: Reaction[ByteBuffer] = Reaction[ByteBuffer] { message =>
    scribe.warn("Received unhandled binary data on WebSocket!")
  }

  webSocket.changes {
    case (oldClient, newClient) => {
      oldClient.foreach { c =>
        c.receive.text.reactions -= receiveText
        c.receive.binary.reactions -= receiveBinary

        c.disconnect()
      }
      newClient.foreach { c =>
        c.receive.text.reactions += receiveText
        c.receive.binary.reactions += receiveBinary
      }
    }
  }
  connection.queue.hasNext.attach(_ => checkQueue())

  def disconnect(): Unit = webSocket := None

  /**
    * Checks for queue entries if connected
    */
  private def checkQueue(): Unit = if (webSocket().exists(_.status() == ConnectionStatus.Open)) {
    val ws = webSocket().get
    connection.queue.next() match {
      case Some(request) => ws.send.text @= Connectivity(request.id, request.`type`, request.json)
      case None => // Nothing in the queue
    }
  }
}

object Connectivity {
  private val PrefixRegex = """\[youi id=(\d+) type=(\S+)\]""".r

  private def apply(id: Long, `type`: MessageType, json: Json): String = {
    s"[youi id=$id type=${`type`.name}]${json.spaces2}"
  }
}
