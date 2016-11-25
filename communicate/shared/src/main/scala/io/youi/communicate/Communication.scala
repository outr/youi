package io.youi.communicate

import scala.concurrent.{Future, Promise}

import scala.language.experimental.macros

import scala.concurrent.ExecutionContext.Implicits.global

trait Communication {
  val interface: Interface

  def send(messageId: Int, invocationId: Int, invocationType: InvocationType, message: Option[String]): Unit
  def receive(messageId: Int, invocationId: Int, invocationType: InvocationType, message: Option[String]): Unit = synchronized {
    invocationType match {
      case InvocationType.CallRequest => {
        val call = Invocation.byId[Call[Any]](invocationId)
        call().foreach { response =>
          val json = call.responsePickler.write(response)
          send(messageId, invocationId, InvocationType.CallResponse, Some(json))
        }
      }
      case InvocationType.CallResponse => {
        val promise = awaitingResponse.getOrElse(messageId, throw new RuntimeException(s"Received messageId: $messageId but nothing awaiting response (${awaitingResponse.keys.mkString(", ")}."))
        awaitingResponse -= messageId
        val call = Invocation.byId[Call[Any]](invocationId)
        val response = call.responsePickler.read(message.get)
        promise.success(response)
      }
      case InvocationType.MethodRequest => {
        val method = Invocation.byId[Method[Any, Any]](invocationId)
        val request = method.requestPickler.read(message.get)
        method(request).foreach { response =>
          val json = method.responsePickler.write(response)
          send(messageId, invocationId, InvocationType.MethodResponse, Some(json))
        }
      }
      case InvocationType.MethodResponse => {
        val promise = awaitingResponse.getOrElse(messageId, throw new RuntimeException(s"Received messageId: $messageId but nothing awaitingResponse (${awaitingResponse.keys.mkString(", ")}."))
        awaitingResponse -= messageId
        val method = Invocation.byId[Method[Any, Any]](invocationId)
        val response = method.responsePickler.read(message.get)
        promise.success(response)
      }
    }
  }

  private var messageIdGenerator = 0
  private def nextMessageId(): Int = synchronized {
    messageIdGenerator += 1
    messageIdGenerator
  }

  private var awaitingResponse = Map.empty[Int, Promise[Any]]

  def call[Response](call: Call[Response]): Future[Response] = synchronized {
    val messageId = nextMessageId()
    val promise = Promise[Response]
    awaitingResponse += messageId -> promise.asInstanceOf[Promise[Any]]
    send(messageId, call.id, InvocationType.CallRequest, None)
    promise.future
  }

  def method[Request, Response](method: Method[Request, Response], request: Request): Future[Response] = synchronized {
    val messageId = nextMessageId()
    val promise = Promise[Response]
    awaitingResponse += messageId -> promise.asInstanceOf[Promise[Any]]
    val json = method.requestPickler.write(request)
    send(messageId, method.id, InvocationType.MethodRequest, Some(json))
    promise.future
  }

  def server[I <: Interface]: I = macro Macros.server[I]
}