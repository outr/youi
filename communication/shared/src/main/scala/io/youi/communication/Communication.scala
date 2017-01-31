package io.youi.communication

import java.util.concurrent.atomic.AtomicInteger

import com.outr.reactify.{Channel, Var}
import com.outr.scribe._
import io.youi.http.Connection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.language.experimental.macros

trait Communication {
  val comm: CommunicationInternal = new CommunicationInternal(this)
  comm.init()

  def connection: Connection

  def shared[T](default: T): Var[T] = macro Macros.shared[T]
}

class CommunicationInternal private[communication](communication: Communication) {
  private val increment = new AtomicInteger(0)
  private var queue = Map.empty[Int, CommunicationMessage => Unit]

  val send: Channel[CommunicationMessage] = Channel[CommunicationMessage]
  val receive: Channel[CommunicationMessage] = Channel[CommunicationMessage]

  private[communication] def init(): Unit = {
    receive.attach { message =>
      if (message.messageType == CommunicationMessage.MethodResponse) {
        synchronized {
          val f = queue.get(message.invocationId)
          queue -= message.invocationId
          f
        } match {
          case Some(f) => f(message)
          case None => logger.warn(s"No entry found for endPointId: ${message.endPointId}, invocationId: ${message.invocationId}, content: ${message.content}.")
        }
      }
    }

    communication.connection.receive.text.attach {
      case CommunicationMessage(message) => receive := message
    }
    send.attach { message =>
      communication.connection.send.text := message.parsableString
    }
  }

  def nextId(): Int = increment.getAndIncrement()

  def onEndPoint[T](endPointId: Int)(f: CommunicationMessage => Future[String]): Unit = {
    receive.attach { message =>
      if (message.endPointId == endPointId && message.messageType == CommunicationMessage.MethodRequest) {
        f(message).map { content =>
          send := CommunicationMessage(CommunicationMessage.MethodResponse, endPointId, message.invocationId, List(content))
        }
      }
    }
  }

  def onInvocation[T](invocationId: Int)(f: CommunicationMessage => T): Future[T] = synchronized {
    val promise = Promise[T]
    val handler: CommunicationMessage => Unit = (m: CommunicationMessage) => {
      val t = f(m)
      promise.success(f(m))
    }
    queue += invocationId -> handler
    promise.future
  }
}

object Communication {
  private val increment = new AtomicInteger(0)

  def nextEndPointId: Int = increment.getAndIncrement()

  def create[C <: Communication](connection: Connection): C = macro Macros.create[C]
}