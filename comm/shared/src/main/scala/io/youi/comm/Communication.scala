package io.youi.comm

import com.outr.scribe._
import com.outr.reactify.Channel

import scala.concurrent.{Future, Promise}
import scala.language.experimental.macros

import scala.concurrent.ExecutionContext.Implicits.global

trait Communication {
  object comm {
    val id: Int = Communication.nextId
    private var increment: Int = -1
    private var queue = Map.empty[Int, CommunicationMessage => Unit]

    val send: Channel[CommunicationMessage] = Channel[CommunicationMessage]
    val receive: Channel[CommunicationMessage] = Channel[CommunicationMessage]

    receive.attach { message =>
      if (message.messageType == CommunicationMessage.MethodResponse) {
        synchronized {
          val f = queue.get(message.invocationId)
          queue -= message.invocationId
          f
        } match {
          case Some(f) => f(message)
          case None => logger.warn(s"No entry found for communicationId: ${message.communicationId}, endPointId: ${message.endPointId}, invocationId: ${message.invocationId}, content: ${message.content}.")
        }
      }
    }

    def nextId: Int = synchronized {
      increment += 1
      increment
    }

    def onEndPoint[T](endPointId: Int)(f: CommunicationMessage => Future[String]): Unit = {
      receive.attach { message =>
        if (message.endPointId == endPointId && message.messageType == CommunicationMessage.MethodRequest) {
          f(message).map { content =>
            send := CommunicationMessage(CommunicationMessage.MethodResponse, id, endPointId, message.invocationId, List(content))
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
}

object Communication {
  private var increment: Int = -1

  private def nextId: Int = synchronized {
    increment += 1
    increment
  }

  def create[C <: Communication]: C = macro Macros.create[C]
}