package io.youi.communicate

import scala.concurrent.Future

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
  def clientRequest[Response](methodName: String): CommunicationCall[Response]

  /**
    * Server sends `Request` to the Client and receives `Response` from the Client.
    *
    * @param methodName the method name that must be implemented on the client.
    *                   (ex. def methodName(request: Request): Response)
    * @tparam Request the Request the method on the client will receive.
    * @tparam Response the Response the method on the client will return.
    * @return CommunicationMethod[Request, Response]
    */
  def clientMethod[Request, Response](methodName: String): CommunicationMethod[Request, Response]

  /**
    * Client requests `Response` from the Server.
    *
    * @param methodName the method name that must be implemented on the server.
    *                   (ex. def methodName: Response)
    * @tparam Response the Response type expected from the server method.
    * @return CommunicationCall[Response]
    */
  def serverRequest[Response](methodName: String): CommunicationCall[Response]

  /**
    * Client sends `Request` to the Server and receives `Response` from the Client.
    *
    * @param methodName the method name that must be implemented on the client.
    *                   (ex. def methodName(request: Request): Response)
    * @tparam Request the Request the method on the server will receive.
    * @tparam Response the Response the method on the server will return.
    * @return CommunicationMethod[Request, Response]
    */
  def serverMethod[Request, Response](methodName: String): CommunicationMethod[Request, Response]
}

trait SampleCommunicator extends Communicator {
  // serverMethod, clientMethod, serverRequest, clientRequest

  // Send a String to the Server, reverse it, send it back
  val reverse = serverMethod[String, String]("reverseImpl")
  // Ask the client for the browser's URL
  val url = clientRequest[String]("urlImpl")
}

trait CommunicationMethod[Request, Response] {
  def apply(request: Request): Future[Response]
}

trait CommunicationCall[Response] {
  def apply(): Future[Response]
}