package io.youi.communication

import fabric._
import fabric.rw._
import scribe.Execution.global

import scala.concurrent.{Future, Promise}
import scala.language.experimental.macros
import scala.util.{Failure, Success}

trait Hookup[Interface] {
  def name: String = throw new NotImplementedError("This will be implemented by HookupMacros")
  def connection: Connection = throw new NotImplementedError("This will be implemented by HookupMacros")
  def local: Map[String, Message => Future[Json]] = throw new NotImplementedError("This will be implemented by HookupMacros")
  def instance: Interface = throw new NotImplementedError("This will be implemented by HookupMacros")

  // Register the hookup with the connection
  connection.hookups.register(this)

  /**
    * Supply JSON to invoke a local method on Interface and return Future[Json]
    *
    * @param message the Message
    * @return Future[Json] of the return
    */
  def receive(message: Message): Future[Message] = try {
    val method = local.getOrElse(message.method.get, throw new RuntimeException(s"No local method found for name: ${message.method} (${local.keySet.mkString(", ")})"))
    val promise = Promise[Message]()
    method(message).onComplete {
      case Success(response) => promise.success(Message.response(message.id, message.name.get, message.method.get, response))
      case Failure(throwable) => {
        scribe.error(s"Error processing method request future: $message", throwable)
        promise.success(Message.error(message.id, throwable.getMessage))
      }
    }
    promise.future
  } catch {
    case t: Throwable => {
      scribe.error(s"Error processing method request: $message", t)
      Future.successful(Message.error(message.id, t.getMessage))
    }
  }
}