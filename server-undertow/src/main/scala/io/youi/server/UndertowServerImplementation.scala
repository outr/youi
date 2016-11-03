package io.youi.server

import io.undertow.{Undertow, UndertowOptions}
import io.undertow.server.{HttpServerExchange, HttpHandler => UndertowHttpHandler}
import io.youi.http.{Headers, HttpRequest, HttpResponse, Method}
import io.youi.net.URL

class UndertowServerImplementation(server: Server) extends ServerImplementation with UndertowHttpHandler {
  private var instance: Option[Undertow] = None

  override def start(): Unit = synchronized {
    val u = Undertow.builder()
      .setServerOption(UndertowOptions.ENABLE_HTTP2, java.lang.Boolean.TRUE)
      .addHttpListener(server.config.port.get, server.config.host.get)
      .setHandler(this)
      .build()
    u.start()
    instance = Some(u)
  }

  override def stop(): Unit = synchronized {
    instance match {
      case Some(u) => {
        u.stop()
        instance = None
      }
      case None => // Not running
    }
  }

  override def isRunning: Boolean = instance.nonEmpty

  override def handleRequest(exchange: HttpServerExchange): Unit = {
    val request = UndertowServerImplementation.request(exchange)
    val response = server.handle(request, HttpResponse())
    UndertowServerImplementation.response(response, exchange)
  }
}

object UndertowServerImplementation {
  def request(exchange: HttpServerExchange): HttpRequest = HttpRequest(
    method = Method(exchange.getRequestMethod.toString),
    url = URL(exchange.getRequestURL),
    headers = Headers.empty,    // TODO: implement
    content = None              // TODO: implement
  )

  def response(response: HttpResponse, exchange: HttpServerExchange): Unit = {
    // TODO: implement
  }
}