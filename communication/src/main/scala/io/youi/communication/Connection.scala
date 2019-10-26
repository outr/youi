package io.youi.communication

import java.nio.ByteBuffer
import io.youi.http.{ConnectionStatus, WebSocket}
import profig.JsonUtil
import reactify.{Val, Var}
import reactify.reaction.Reaction

import scala.concurrent.{ExecutionContext, Future}
import scala.language.experimental.macros
import scribe.Execution.global

trait Connection {
  val webSocket: Var[Option[WebSocket]] = Var(None)
  val queue: HookupQueue = new HookupQueue
  val status: Val[ConnectionStatus] = Val(webSocket().map(_.status()).getOrElse(ConnectionStatus.Closed))
  val lastActive: Val[Long] = Var[Long](0L)

  // Created for binary output and removed upon completion
  private var writer: Option[ByteBufferWriter] = None

  object hookups {
    private var map = Map.empty[String, Hookup[Any]]

    def register[Interface](hookup: Hookup[Interface]): Unit = synchronized {
      map += hookup.name -> hookup.asInstanceOf[Hookup[Any]]
    }

    def byName(name: String): Hookup[Any] = map.getOrElse(name, throw new RuntimeException(s"Unable to find hookup by name: $name (names: ${map.keySet.mkString(", ")})"))
  }

  def receive(message: Message): Future[Message] = {
    val hookup = hookups.byName(message.name.get)
    hookup.receive(message)
  }

  def upload(fileName: String, bytes: Long): Future[String] = {
    queue.enqueue(Message.uploadStart(fileName, bytes)).map { response =>
      response.name.get
    }
  }

  protected def interface[Interface](implicit ec: ExecutionContext): Interface with Hookup[Interface] = macro HookupMacros.interface[Interface]
  protected def implementation[Interface, Implementation <: Interface](implicit ec: ExecutionContext): Implementation with Hookup[Interface] = macro HookupMacros.implementation[Interface, Implementation]

  private val receiveText: Reaction[String] = Reaction[String] { text =>
    lastActive.asInstanceOf[Var[Long]] @= System.currentTimeMillis()
    text match {
      case "PING" => webSocket.foreach(_.send.text @= "PONG")
      case "PONG" => // Ignore keep-alive
      case _ if text.startsWith("{") && text.endsWith("}") => {
        val message = JsonUtil.fromJsonString[Message](text)
        message.`type` match {
          case MessageType.Invoke => receive(message).foreach { response =>
            queue.enqueue(response)
          }
          case MessageType.Response => if (queue.success(message)) {
            // Success
          } else {
            scribe.warn(s"No id found for ${message.id}. Cannot apply: $message")
          }
          case MessageType.UploadStart => {
            val w = CommunicationPlatform.createWriter(message.name.get, message.bytes.get)
            writer = Some(w)
            w.promise.future.foreach { _ =>
              queue.enqueue(Message.uploadComplete(message.id, w.actualFileName))
            }
          }
          case MessageType.UploadComplete => if (queue.success(message)) {
            // Success
          } else {
            scribe.warn(s"No id found for ${message.id}. Cannot apply: $message")
          }
          case MessageType.Error => if (queue.failure(message.id, new RuntimeException(message.toString))) {
            // Success
          } else {
            scribe.warn(s"No id found for ${message.id}. Cannot fail: $message")
          }
        }
      }
      case _ => scribe.warn(s"Unhandled: $text")
    }
  }

  private val receiveBinary: Reaction[ByteBuffer] = Reaction[ByteBuffer] { message =>
    lastActive.asInstanceOf[Var[Long]] @= System.currentTimeMillis()

    writer match {
      case Some(w) => {
        w.write(message)
        // TODO: support MessageType.UploadStatus
        if (w.remaining == 0L) {
          w.close()
          w.promise.success(())
        }
      }
      case None => scribe.info("No writer assigned!")
    }
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
    case (oldStatus, newStatus) => if (oldStatus == ConnectionStatus.Open) {
      lastActive.asInstanceOf[Var[Long]] @= System.currentTimeMillis()
    } else if (newStatus == ConnectionStatus.Open) {
      checkQueue()
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
      case Some(request) => ws.send.text @= JsonUtil.toJsonString(request.request)
      case None => // Nothing in the queue
    }
  }
}