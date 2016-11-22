package io.youi.communicate

import scala.annotation.{StaticAnnotation, compileTimeOnly}
import scala.language.experimental.macros
import scala.reflect.macros.blackbox
import scala.concurrent.Future

/*
  perhaps I should move the Macros to another level:
    object Server {
      val communicator = server[Example](this)
      def serverTime: Long = System.currentTimeMillis()
    }
 */

/**
  * Communicator represents a base trait that must be defined in a shared source between client and server. The
  * implementing classes for client and server will be expected to follow the naming scheme of the shared trait
  * but start with a "Client" or "Server" respectively.
  */
trait Communicator {
  /**
    * Server requests `Response` from the Client.
    *
    * @param methodName the method name that must be implemented on the client.
    *                   (ex. def methodName: Response)
    * @tparam Response the Response type expected from the client method.
    * @return CommunicationCall[Response]
    */
//  def clientRequest[Response](methodName: String): CommunicationCall[Response]

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
    * @param methodName the method name that must be implemented on the client.
    *                   (ex. def methodName(request: Request): Response)
    * @tparam Request the Request the method on the server will receive.
    * @tparam Response the Response the method on the server will return.
    * @return CommunicationMethod[Request, Response]
    */
//  def serverMethod[Request, Response](methodName: String): CommunicationMethod[Request, Response]
}

trait CommunicationImplementation {
  def server[C <: Communicator]: C = macro CommunicatorMacros.server[C]
}

@compileTimeOnly("Enable Macros for expansion")
object CommunicatorMacros {
  def serverRequest[Response](c: blackbox.Context)(implicit response: c.WeakTypeTag[Response]): c.Expr[ServerCommunicationCall[Response]] = {
    import c.universe._

    c.Expr[ServerCommunicationCall[Response]](
      q"""
         new io.youi.communicate.ServerCommunicationCall[$response] {}
       """)
  }

  def server[C <: Communicator](context: blackbox.Context)(implicit c: context.WeakTypeTag[C]): context.Expr[C] = {
    import context.universe._

    val typesSet = Set("io.youi.communicate.ServerCommunicationCall")
    val server = context.prefix.tree
    val fields = c.tpe.decls.collect {
      case v: TermSymbol if v.isVal && typesSet.contains(v.info.typeSymbol.fullName) => {
        val responseType = v.info.resultType.typeArgs.head
        q"""
          override val ${v.name} = {
            new ${v.info.resultType.typeSymbol}[$responseType] {
              override val responsePickler = new io.youi.communicate.Pickler[$responseType] {
                override def read(json: String): $responseType = upickle.default.read[$responseType](json)
                override def write(t: $responseType): String = upickle.default.write[$responseType](t)
              }
              override def apply() = scala.concurrent.Future($server.${v.name})
            }
          }
         """
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

trait CommunicationMethod[Request, Response] {
  def apply(request: Request): Future[Response]
}

trait CommunicationCall[Response] {
  def responsePickler: Pickler[Response] = throw new UnsupportedOperationException("Not implemented.")

  def apply(): Future[Response] = throw new UnsupportedOperationException("Not implemented.")
}

trait ServerCommunicationCall[Response] extends CommunicationCall[Response]

trait Pickler[T] {
  def read(json: String): T
  def write(t: T): String
}

object Pickler {
  private var increment: Int = 0

  private def next(): Int = {
    increment += 1
    increment
  }
}