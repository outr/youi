package io.youi.comm

import com.outr.scribe._
import com.outr.reactify.Channel

import scala.concurrent.{Future, Promise}
import scala.language.experimental.macros

trait Communication {
  object comm {
    private var increment: Int = -1
    private var queue = Map.empty[Int, CommunicationMessage => Unit]

    val send: Channel[CommunicationMessage] = Channel[CommunicationMessage]
    val receive: Channel[CommunicationMessage] = Channel[CommunicationMessage]

    receive.attach { message =>
      synchronized {
        val f = queue.get(message.id)
        queue -= message.id
        f
      } match {
        case Some(f) => f(message)
        case None => logger.warn(s"No entry found for id: ${message.id}, content: ${message.content}.")
      }
    }

    def nextId: Int = synchronized {
      increment += 1
      increment
    }

    def onId[T](id: Int)(f: CommunicationMessage => T): Future[T] = synchronized {
      val promise = Promise[T]
      val handler: CommunicationMessage => Unit = (m: CommunicationMessage) => {
        val t = f(m)
        promise.success(f(m))
      }
      queue += id -> handler
      promise.future
    }
  }
}

object Communication {
  def create[C <: Communication]: C = macro Macros.create[C]
}