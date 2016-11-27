package io.youi.server

import java.io.IOException
import java.net.URI
import java.nio.channels.{Channels, FileChannel}
import java.nio.file.StandardOpenOption

import io.undertow.io.{IoCallback, Sender}
import io.undertow.server.handlers.proxy.SimpleProxyClientProvider
import io.undertow.server.handlers.resource.URLResource
import io.undertow.{Handlers, Undertow, UndertowOptions}
import io.undertow.server.{HttpServerExchange, HttpHandler => UndertowHttpHandler}
import io.undertow.util.HttpString
import io.undertow.websockets.WebSocketConnectionCallback
import io.undertow.websockets.core._
import io.undertow.websockets.spi.WebSocketHttpExchange
import io.youi.http.{FileContent, Headers, HttpConnection, HttpRequest, HttpResponse, Method, StringContent, URLContent, WebSocketListener}
import io.youi.net.{Parameters, Path, URL}

import scala.collection.JavaConverters._

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

  override def handleRequest(exchange: HttpServerExchange): Unit = server.errorSupport {
    val request = UndertowServerImplementation.request(exchange)
    val connection = new HttpConnection(request)
    server.handle(connection)
    UndertowServerImplementation.response(server, connection, exchange)
  }
}

object UndertowServerImplementation {
  def request(exchange: HttpServerExchange): HttpRequest = {
    val headers = Headers(exchange.getRequestHeaders.asScala.map { hv =>
      hv.getHeaderName.toString -> hv.asScala.toList
    }.toMap)
    HttpRequest(
      method = Method(exchange.getRequestMethod.toString),
      url = URL(s"${exchange.getRequestURL}?${exchange.getQueryString}"),
      headers = headers,
      content = None // TODO: implement
    )
  }

  def response(server: Server, connection: HttpConnection, exchange: HttpServerExchange): Unit = {
    connection.webSocketSupport match {
      case Some(webSocketListener) => handleWebSocket(webSocketListener, exchange)
      case None => connection.proxySupport.flatMap(_.proxy(connection)) match {
        case Some(destination) => handleProxy(server, connection, exchange, destination)
        case None => handleStandard(server, connection, exchange)
      }
    }
  }

  private def handleWebSocket(webSocketListener: WebSocketListener, exchange: HttpServerExchange): Unit = {
    val handler = Handlers.websocket(new WebSocketConnectionCallback {
      override def onConnect(exchange: WebSocketHttpExchange, channel: WebSocketChannel): Unit = {
        // Handle sending messages
        webSocketListener.send.text.attach { message =>
          WebSockets.sendText(message, channel, null)
        }
        webSocketListener.send.binary.attach { message =>
          WebSockets.sendBinary(message, channel, null)
        }
        webSocketListener.send.close.attach { _ =>
          channel.sendClose()
        }

        // Handle receiving messages
        channel.getReceiveSetter.set(new AbstractReceiveListener {
          override def onFullTextMessage(channel: WebSocketChannel, message: BufferedTextMessage): Unit = {
            webSocketListener.receive.text := message.getData
            super.onFullTextMessage(channel, message)
          }

          override def onFullBinaryMessage(channel: WebSocketChannel, message: BufferedBinaryMessage): Unit = {
            webSocketListener.receive.binary := message.getData.getResource
            super.onFullBinaryMessage(channel, message)
          }

          override def onError(channel: WebSocketChannel, error: Throwable): Unit = {
            webSocketListener.error := error
            super.onError(channel, error)
          }

          override def onFullCloseMessage(channel: WebSocketChannel, message: BufferedBinaryMessage): Unit = {
            webSocketListener.receive.close := Unit
            super.onFullCloseMessage(channel, message)
          }
        })
        channel.resumeReceives()
      }
    })
    handler.handleRequest(exchange)
  }

  private def handleProxy(server: Server, connection: HttpConnection, exchange: HttpServerExchange, destination: URL): Unit = {
    val client = new SimpleProxyClientProvider(new URI(destination.copy(path = Path.empty, parameters = Parameters.empty, fragment = None).toString))
    val handler = Handlers.proxyHandler(client)
    exchange.setRequestPath(destination.path.encoded)
    exchange.setRequestURI(destination.path.encoded)
    println(s"Request Path: ${exchange.getRequestPath}, ${exchange.getRequestURI}, ${exchange.getRequestURL}")
    handler.handleRequest(exchange)
  }

  private def handleStandard(server: Server, connection: HttpConnection, exchange: HttpServerExchange): Unit = {
    val response = connection.response

    exchange.setStatusCode(response.status.code)
    response.headers.map.foreach {
      case (key, values) => exchange.getResponseHeaders.putAll(new HttpString(key), values.asJava)
    }
    if (exchange.getRequestMethod.toString != "HEAD") {
      response.content match {
        case Some(content) => content match {
          case StringContent(s, contentType, lastModified) => exchange.getResponseSender.send(s)
          case FileContent(file, contentType) => {
            val channel = FileChannel.open(file.getAbsoluteFile.toPath, StandardOpenOption.READ)
            exchange.getResponseSender.transferFrom(channel, new IoCallback {
              override def onComplete(exchange: HttpServerExchange, sender: Sender): Unit = {
                channel.close()
                sender.close()
              }

              override def onException(exchange: HttpServerExchange, sender: Sender, exception: IOException): Unit = {
                channel.close()
                sender.close()
                server.error(exception)
              }
            })
          }
          case URLContent(url, contentType) => {
            val connection = url.openConnection()
            val resource = new URLResource(url, connection, "")
            resource.serve(exchange.getResponseSender, exchange, new IoCallback {
              override def onComplete(exchange: HttpServerExchange, sender: Sender): Unit = {
                sender.close()
              }

              override def onException(exchange: HttpServerExchange, sender: Sender, exception: IOException): Unit = {
                sender.close()
                server.error(exception)
              }
            })
          }
        }
        case None => exchange.getResponseSender.send("", new IoCallback {
          override def onComplete(exchange: HttpServerExchange, sender: Sender): Unit = {
            sender.close()
          }

          override def onException(exchange: HttpServerExchange, sender: Sender, exception: IOException): Unit = {
            sender.close()
            server.error(exception)
          }
        })
      }
    }
  }
}