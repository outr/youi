package io.youi.communication

import java.nio.ByteBuffer

import io.circe.Json
import io.circe.parser.parse
import io.youi.http.{ConnectionStatus, WebSocket}
import reactify.{Val, Var}
import reactify.reaction.Reaction

import scala.concurrent.Future
import scala.language.experimental.macros
import scala.util.{Failure, Success}
import scribe.Execution.global

trait Connection {
  val webSocket: Var[Option[WebSocket]] = Var(None)
  val queue: HookupQueue = new HookupQueue
  val status: Val[ConnectionStatus] = Val(webSocket().map(_.status()).getOrElse(ConnectionStatus.Closed))
  val lastActive: Val[Long] = Var[Long](0L)

  object hookups {
    private var map = Map.empty[String, Hookup[Any]]

    def register[Interface](hookup: Hookup[Interface]): Unit = synchronized {
      map += hookup.name -> hookup.asInstanceOf[Hookup[Any]]
    }

    def byName(name: String): Hookup[Any] = map.getOrElse(name, throw new RuntimeException(s"Unable to find hookup by name: $name (names: ${map.keySet.mkString(", ")})"))
  }

  def receive(json: Json): Future[Json] = {
    val endPoint = (json \\ "endpoint").head.asString.getOrElse(throw new RuntimeException(s"No 'method' entry defined for: $json"))
    val name = endPoint.substring(0, endPoint.indexOf('.'))
    val hookup = hookups.byName(name)
    hookup.receive(json)
  }

  protected def interface[Interface]: Interface with Hookup[Interface] = macro HookupMacros.interface[Interface]
  protected def implementation[Interface, Implementation <: Interface]: Implementation with Hookup[Interface] = macro HookupMacros.implementation[Interface, Implementation]

  private val receiveText: Reaction[String] = Reaction[String] { message =>
    lastActive.asInstanceOf[Var[Long]] @= System.currentTimeMillis()
    message match {
      case "PING" => webSocket.foreach(_.send.text @= "PONG")
      case "PONG" => // Ignore keep-alive
      case _ if message.startsWith("[youi id=") => {
        val splitPoint = message.indexOf(']')
        val (id, typ) = message.substring(0, splitPoint + 1) match {
          case Connection.PrefixRegex(i, t) => (i.toLong, t)
        }
        val messageType = MessageType.byName(typ)
        val content = message.substring(splitPoint + 1)
        val json = parse(content).getOrElse(throw new RuntimeException(s"Unable to parse JSON for $id ($typ): $content"))
        messageType match {
          case MessageType.Invoke => receive(json).onComplete {
            case Success(response) => queue.enqueue(MessageType.Response, response, id)
            case Failure(throwable) => {
              scribe.error(s"Error while processing method request: $json", throwable)
              queue.enqueue(MessageType.Error, Json.fromString(throwable.getMessage), id)
            }
          }
          case MessageType.Response => if (queue.success(id, json)) {
            // Success
          } else {
            scribe.warn(s"No id found for $id. Cannot apply: $json")
          }
          case MessageType.Error => if (queue.failure(id, new RuntimeException(json.asString.getOrElse(json.toString())))) {
            // Success
          } else {
            scribe.warn(s"No id found for $id. Cannot fail: $json")
          }
        }
      }
      case _ => scribe.warn(s"Unhandled: $message")
    }
  }

  private val receiveBinary: Reaction[ByteBuffer] = Reaction[ByteBuffer] { message =>
    lastActive.asInstanceOf[Var[Long]] @= System.currentTimeMillis()
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
  queue.hasNext.attach(_ => checkQueue())
  status.changes {
    case (oldStatus, _) => if (oldStatus == ConnectionStatus.Open) {
      lastActive.asInstanceOf[Var[Long]] @= System.currentTimeMillis()
    }
  }

  def disconnect(): Unit = webSocket := None

  def dispose(): Unit = {
    disconnect()
    queue.dispose()
  }

  /**
    * Checks for queue entries if connected
    */
  private def checkQueue(): Unit = if (webSocket().exists(_.status() == ConnectionStatus.Open)) {
    val ws = webSocket().get
    queue.next() match {
      case Some(request) => ws.send.text @= Connection(request.id, request.`type`, request.json)
      case None => // Nothing in the queue
    }
  }
}

object Connection {
  private val PrefixRegex = """\[youi id=(\d+) type=(\S+)\]""".r

  private def apply(id: Long, `type`: MessageType, json: Json): String = {
    s"[youi id=$id type=${`type`.name}]${json.spaces2}"
  }
}