package io.youi.communicate

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.language.experimental.macros

trait Communication[Context] {
  val interface: Interface

  private var messageIdGenerator = 0
  private var awaitingResponse = Map.empty[Int, Promise[Any]]

  private val _context = new ThreadLocal[Option[Context]] {
    override def initialValue(): Option[Context] = None
  }
  def contextOption: Option[Context] = _context.get
  def context: Context = contextOption.getOrElse(throw new RuntimeException(s"No context available on current thread."))
  def contextualize[R](context: Context)(f: => R): R = {
    val previous = _context.get
    _context.set(Some(context))
    try {
      f
    } finally {
      if (previous.nonEmpty) {
        _context.set(previous)
      } else {
        _context.remove()
      }
    }
  }

  def send(message: CommunicationMessage): Unit
  def receive(message: CommunicationMessage): Unit = synchronized {
    message.invocationType match {
      case InvocationType.CallRequest => {
        val context = this.context
        val call = Invocation.byId[Call[Any]](message.invocationId)
        call().foreach { response =>
          contextualize(context) {
            val json = call.responsePickler.write(response)
            send(message.copy(invocationType = InvocationType.CallResponse, content = Some(json)))
          }
        }
      }
      case InvocationType.CallResponse => {
        val promise = awaitingResponse.getOrElse(message.id, throw new RuntimeException(s"Received messageId: ${message.id} but nothing awaiting response (${awaitingResponse.keys.mkString(", ")}."))
        awaitingResponse -= message.id
        val call = Invocation.byId[Call[Any]](message.invocationId)
        val response = call.responsePickler.read(message.content.get)
        promise.success(response)
      }
      case InvocationType.MethodRequest => {
        val context = this.context
        val method = Invocation.byId[Method[Any, Any]](message.invocationId)
        val request = method.requestPickler.read(message.content.get)
        method(request).foreach { response =>
          contextualize(context) {
            val json = method.responsePickler.write(response)
            send(message.copy(invocationType = InvocationType.MethodResponse, content = Some(json)))
          }
        }
      }
      case InvocationType.MethodResponse => {
        val promise = awaitingResponse.getOrElse(message.id, throw new RuntimeException(s"Received messageId: ${message.id} but nothing awaitingResponse (${awaitingResponse.keys.mkString(", ")}."))
        awaitingResponse -= message.id
        val method = Invocation.byId[Method[Any, Any]](message.invocationId)
        val response = method.responsePickler.read(message.content.get)
        promise.success(response)
      }
    }
  }

  private def nextMessageId(): Int = synchronized {
    messageIdGenerator += 1
    messageIdGenerator
  }

  def call[Response](call: Call[Response]): Future[Response] = synchronized {
    val messageId = nextMessageId()
    val promise = Promise[Response]
    awaitingResponse += messageId -> promise.asInstanceOf[Promise[Any]]
    send(CommunicationMessage(messageId, call.id, InvocationType.CallRequest, None))
    promise.future
  }

  def method[Request, Response](method: Method[Request, Response], request: Request): Future[Response] = synchronized {
    val messageId = nextMessageId()
    val promise = Promise[Response]
    awaitingResponse += messageId -> promise.asInstanceOf[Promise[Any]]
    val json = method.requestPickler.write(request)
    send(CommunicationMessage(messageId, method.id, InvocationType.MethodRequest, Some(json)))
    promise.future
  }

  def server[I <: Interface]: I = macro Macros.server[I]

  def client[I <: Interface]: I = macro Macros.client[I]
}