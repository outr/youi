package io.youi.communicate

import scala.annotation.{StaticAnnotation, compileTimeOnly}
import scala.language.experimental.macros
import scala.reflect.macros.blackbox
import scala.concurrent.{Future, Promise}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Communicator represents a base trait that must be defined in a shared source between client and server. The
  * implementing classes for client and server will be expected to follow the naming scheme of the shared trait
  * but start with a "Client" or "Server" respectively.
  */
trait Communicator {
  /**
    * Server requests `Response` from the Client.
    *
    *                   (ex. def methodName: Response)
    * @tparam Response the Response type expected from the client method.
    * @return CommunicationCall[Response]
    */
  def clientRequest[Response]: ClientCommunicationCall[Response] = macro CommunicatorMacros.clientRequest[Response]

  /**
    * Server sends `Request` to the Client and receives `Response` from the Client.
    *
    * @param methodName the method name that must be implemented on the client.
    *                   (ex. def methodName(request: Request): Response)
    * @tparam Request the Request the method on the client will receive.
    * @tparam Response the Response the method on the client will return.
    * @return CommunicationMethod[Request, Response]
    */
//  def clientMethod[Request, Response](methodName: String): CommunicationMethod[Request, Response]

  /**
    * Client requests `Response` from the Server. The Server must implement a method matching the name of val.
    *
    * @tparam Response the Response type expected from the server method.
    * @return ServerCommunicationCall[Response]
    */
  def serverRequest[Response]: ServerCommunicationCall[Response] = macro CommunicatorMacros.serverRequest[Response]

  /**
    * Client sends `Request` to the Server and receives `Response` from the Client.
    *
    * @tparam Request the Request the method on the server will receive.
    * @tparam Response the Response the method on the server will return.
    * @return CommunicationMethod[Request, Response]
    */
  def serverMethod[Request, Response]: ServerCommunicationMethod[Request, Response] = macro CommunicatorMacros.serverMethod[Request, Response]
}

trait CommunicationImplementation {
  def communicator: Communicator

  private var increment = 0
  private def nextId(): Int = synchronized {
    increment += 1
    increment
  }
  private var awaitingResponse = Map.empty[Int, Promise[Any]]

  def call[Response](communication: CommunicationCall[Response]): Future[Response] = synchronized {
    val messageId = nextId()
    val promise = Promise[Response]
    awaitingResponse += messageId -> promise.asInstanceOf[Promise[Any]]
    send(messageId, communication.id, Communication.CallRequest, None)
    promise.future
  }

  def method[Request, Response](communication: CommunicationMethod[Request, Response], request: Request): Future[Response] = synchronized {
    val messageId = nextId()
    val promise = Promise[Response]
    awaitingResponse += messageId -> promise.asInstanceOf[Promise[Any]]
    val json = communication.requestPickler.write(request)
    send(messageId, communication.id, Communication.MethodRequest, Some(json))
    promise.future
  }

  def send(messageId: Int, communicationId: Int, communicationType: Int, message: Option[String]): Unit
  def receive(messageId: Int, communicationId: Int, communicationType: Int, message: Option[String]): Unit = communicationType match {
    case Communication.CallRequest => {
      val call = Communication.byId[CommunicationCall[Any]](communicationId)
      call().foreach { response =>
        send(messageId, communicationId, Communication.CallResponse, Some(call.responsePickler.write(response)))
      }
    }
    case Communication.CallResponse => {
      val promise = awaitingResponse.getOrElse(messageId, throw new RuntimeException(s"Received messageId: $messageId but nothing awaitingResponse (${awaitingResponse.keys.mkString(", ")})."))
      val call = Communication.byId[CommunicationCall[Any]](communicationId)
      val response = call.responsePickler.read(message.get)
      promise.success(response)
    }
    case Communication.MethodRequest => {
      val method = Communication.byId[CommunicationMethod[Any, Any]](communicationId)
      val request = method.requestPickler.read(message.get)
      method(request).foreach { response =>
        send(messageId, communicationId, Communication.MethodResponse, Some(method.responsePickler.write(response)))
      }
    }
    case Communication.MethodResponse => {
      val promise = awaitingResponse.getOrElse(messageId, throw new RuntimeException(s"Received messageId: $messageId but nothing awaitingResponse (${awaitingResponse.keys.mkString(", ")}."))
      val method = Communication.byId[CommunicationMethod[Any, Any]](communicationId)
      val response = method.responsePickler.read(message.get)
      promise.success(response)
    }
  }

  def server[C <: Communicator]: C = macro CommunicatorMacros.server[C]
}

@compileTimeOnly("Enable Macros for expansion")
object CommunicatorMacros {
  def clientRequest[Response](c: blackbox.Context)(implicit response: c.WeakTypeTag[Response]): c.Expr[ClientCommunicationCall[Response]] = {
    import c.universe._

    c.Expr[ClientCommunicationCall[Response]](q"new io.youi.communicate.ClientCommunicationCall[$response] {}")
  }

  def serverRequest[Response](c: blackbox.Context)(implicit response: c.WeakTypeTag[Response]): c.Expr[ServerCommunicationCall[Response]] = {
    import c.universe._

    c.Expr[ServerCommunicationCall[Response]](q"new io.youi.communicate.ServerCommunicationCall[$response] {}")
  }

  def serverMethod[Request, Response](c: blackbox.Context)(implicit request: c.WeakTypeTag[Request], response: c.WeakTypeTag[Response]): c.Expr[ServerCommunicationMethod[Request, Response]] = {
    import c.universe._

    c.Expr[ServerCommunicationMethod[Request, Response]](q"new io.youi.communicate.ServerCommunicationMethod[$request, $response] {}")
  }

  private def localCall(context: blackbox.Context)(instance: context.universe.Tree, v: context.universe.TermSymbol): context.universe.Tree = {
    import context.universe._

    val responseType = v.info.resultType.typeArgs.head
    q"""
      override val ${v.name} = {
        new ${v.info.resultType.typeSymbol}[$responseType] {
          override val responsePickler = new io.youi.communicate.Pickler[$responseType] {
            override def read(json: String): $responseType = upickle.default.read[$responseType](json)
            override def write(t: $responseType): String = upickle.default.write[$responseType](t)
          }
          override def apply() = scala.concurrent.Future($instance.${v.name})
        }
      }
     """
  }

  private def remoteCall(context: blackbox.Context)(instance: context.universe.Tree, v: context.universe.TermSymbol): context.universe.Tree = {
    import context.universe._

    val responseType = v.info.resultType.typeArgs.head
    q"""
      override val ${v.name} = {
        new ${v.info.resultType.typeSymbol}[$responseType] {
          override val responsePickler = new io.youi.communicate.Pickler[$responseType] {
            override def read(json: String): $responseType = upickle.default.read[$responseType](json)
            override def write(t: $responseType): String = upickle.default.write[$responseType](t)
          }
          override def apply() = $instance.call[$responseType](this)
        }
      }
     """
  }

  private def localMethod(context: blackbox.Context)(instance: context.universe.Tree, v: context.universe.TermSymbol): context.universe.Tree = {
    import context.universe._

    val requestType = v.info.resultType.typeArgs.head
    val responseType = v.info.resultType.typeArgs.tail.head
    q"""
      override val ${v.name} = {
        new ${v.info.resultType.typeSymbol}[$requestType, $responseType] {
          override val requestPickler = new io.youi.communicate.Pickler[$requestType] {
            override def read(json: String): $requestType = upickle.default.read[$requestType](json)
            override def write(t: $requestType): String = upickle.default.write[$requestType](t)
          }
          override val responsePickler = new io.youi.communicate.Pickler[$responseType] {
            override def read(json: String): $responseType = upickle.default.read[$responseType](json)
            override def write(t: $responseType): String = upickle.default.write[$responseType](t)
          }
          override def apply(request: $requestType) = scala.concurrent.Future($instance.${v.name}(request))
        }
      }
     """
  }

  def server[C <: Communicator](context: blackbox.Context)(implicit c: context.WeakTypeTag[C]): context.Expr[C] = {
    import context.universe._

    val instance = context.prefix.tree
    val fields = c.tpe.decls.collect {
      case v: TermSymbol if v.isVal && v.info.typeSymbol.fullName == "io.youi.communicate.ClientCommunicationCall" => {
        remoteCall(context)(instance, v)
      }
      case v: TermSymbol if v.isVal && v.info.typeSymbol.fullName == "io.youi.communicate.ServerCommunicationCall" => {
        localCall(context)(instance, v)
      }
//      case v: TermSymbol if v.isVal && v.info.typeSymbol.fullName == "io.youi.communicate.ClientCommunicationMethod" => {
//        remoteMethod(context)(server, v)
//      }
      case v: TermSymbol if v.isVal && v.info.typeSymbol.fullName == "io.youi.communicate.ServerCommunicationMethod" => {
        localMethod(context)(instance, v)
      }
    }
    val communicator =
      q"""
         new $c {
           import scala.concurrent.ExecutionContext.Implicits.global

           ..$fields
         }
       """
    context.Expr[C](communicator)
  }
}

trait Communication {
  val id: Int = Communication.nextId(this)
}

object Communication {
  val CallRequest: Int = 1
  val CallResponse: Int = 2
  val MethodRequest: Int = 3
  val MethodResponse: Int = 4

  private var map = Map.empty[Int, Communication]
  private var increment: Int = 0

  private def nextId(communication: Communication): Int = synchronized {
    increment += 1
    map += increment -> communication
    increment
  }

  def byId[C <: Communication](id: Int): C = map(id).asInstanceOf[C]
}

trait CommunicationMethod[Request, Response] extends Communication {
  def requestPickler: Pickler[Request] = throw new UnsupportedOperationException("Not implemented.")
  def responsePickler: Pickler[Response] = throw new UnsupportedOperationException("Not implemented.")

  def apply(request: Request): Future[Response] = throw new UnsupportedOperationException("Not implemented.")
}

trait CommunicationCall[Response] extends Communication {
  def responsePickler: Pickler[Response] = throw new UnsupportedOperationException("Not implemented.")

  def apply(): Future[Response] = throw new UnsupportedOperationException("Not implemented.")
}

trait ClientCommunicationCall[Response] extends CommunicationCall[Response]

trait ServerCommunicationCall[Response] extends CommunicationCall[Response]

trait ClientCommunicationMethod[Request, Response] extends CommunicationMethod[Request, Response]

trait ServerCommunicationMethod[Request, Response] extends CommunicationMethod[Request, Response]

trait Pickler[T] {
  def read(json: String): T
  def write(t: T): String
}