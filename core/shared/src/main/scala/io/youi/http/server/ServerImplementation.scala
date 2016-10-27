package io.youi.http.server

/**
  * ServerImplementations are responsible for managing the underlying server functionality specific to a server
  * implementation. Incoming requests in the implementation should generate an HttpRequest and a default HttpResponse
  * and then call Server.handle(request, response) to get back an updated HttpResponse. That response should be be
  * be applied to the implementation to handle the server's response.
  */
trait ServerImplementation {
  /**
    * Starts the server. This need not be called directly as it will be invoked explicitly by Server.start().
    */
  def start(): Unit

  /**
    * Stops the server if it is running. This need not be called directly as it will be invoked explicitly by
    * Server.stop(). If the server is already stopped this method should immediately return.
    */
  def stop(): Unit

  /**
    * true if this server implementation is currently running.
    */
  def isRunning: Boolean
}
