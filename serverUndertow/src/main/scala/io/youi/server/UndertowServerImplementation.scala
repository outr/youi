package io.youi.server

import java.io.IOException
import java.nio.channels.{Channels, FileChannel}
import java.nio.file.StandardOpenOption

import io.undertow.io.{IoCallback, Sender}
import io.undertow.{Handlers, Undertow, UndertowOptions}
import io.undertow.server.{HttpServerExchange, HttpHandler => UndertowHttpHandler}
import io.undertow.util.HttpString
import io.undertow.websockets.WebSocketConnectionCallback
import io.undertow.websockets.core.{AbstractReceiveListener, BufferedBinaryMessage, BufferedTextMessage, WebSocketChannel, WebSockets}
import io.undertow.websockets.spi.WebSocketHttpExchange
import io.youi.http.{FileContent, Headers, HttpConnection, HttpRequest, HttpResponse, Method, StringContent, URLContent}
import io.youi.net.URL

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

  override def handleRequest(exchange: HttpServerExchange): Unit = {
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
      content = None              // TODO: implement
    )
  }

  def response(server: Server, connection: HttpConnection, exchange: HttpServerExchange): Unit = {
    connection.webSocketSupport match {
      case Some(webSocketListener) => {         // WebSocket upgrade request with a listener
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
      case None => {                            // Not a WebSocket upgrade request
      val response = connection.response

        exchange.setStatusCode(response.status.code)
        response.headers.map.foreach {
          case (key, values) => exchange.getResponseHeaders.putAll(new HttpString(key), values.asJava)
        }
        if (exchange.getRequestMethod.toString != "HEAD") {
          response.content match {
            case Some(content) => content match {
              case StringContent(s, lastModified) => exchange.getResponseSender.send(s)
              case FileContent(file) => {
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
              case URLContent(url) => {
                //          val in = url.openConnection().getInputStream
                //          val channel = Channels.newChannel(in)
                //          exchange.getResponseSender.transferFrom(channel, new IoCallback {
                //            override def onComplete(exchange: HttpServerExchange, sender: Sender): Unit = channel.close()
                //
                //            override def onException(exchange: HttpServerExchange, sender: Sender, exception: IOException): Unit = server.error(exception)
                //          })
                throw new UnsupportedOperationException(s"URLContent is not currently supported!")
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
  }
}