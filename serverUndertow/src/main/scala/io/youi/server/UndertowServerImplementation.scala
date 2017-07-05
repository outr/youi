package io.youi.server

import java.io.IOException
import java.net.URI

import io.undertow.io.{IoCallback, Sender}
import io.undertow.predicate.Predicates
import io.undertow.protocols.ssl.UndertowXnioSsl
import io.undertow.server.handlers.encoding.{ContentEncodingRepository, DeflateEncodingProvider, EncodingHandler, GzipEncodingProvider}
import io.undertow.server.handlers.form.{FormDataParser, FormParserFactory}
import io.undertow.server.handlers.proxy.LoadBalancingProxyClient
import io.undertow.server.handlers.resource.URLResource
import io.undertow.server.{HttpServerExchange, HttpHandler => UndertowHttpHandler}
import io.undertow.util.{HeaderMap, HttpString}
import io.undertow.websockets.WebSocketConnectionCallback
import io.undertow.websockets.core._
import io.undertow.websockets.spi.WebSocketHttpExchange
import io.undertow.{Handlers, Undertow, UndertowOptions}
import io.youi.http.{Connection, FileContent, FileEntry, FormData, FormDataContent, FormDataEntry, Headers, HttpConnection, HttpRequest, Method, RequestContent, StringContent, StringEntry, URLContent}
import io.youi.net.{ContentType, IP, Parameters, Path, URL}
import io.youi.server.util.SSLUtil
import org.xnio.{OptionMap, Xnio}

import scala.collection.JavaConverters._

class UndertowServerImplementation(server: Server) extends ServerImplementation with UndertowHttpHandler {
  private var instance: Option[Undertow] = None

  override def start(): Unit = synchronized {
    val contentEncodingRepository = new ContentEncodingRepository()
      .addEncodingHandler("gzip", new GzipEncodingProvider, 100, Predicates.maxContentSize(5L))
      .addEncodingHandler("deflate", new DeflateEncodingProvider, 50, Predicates.maxContentSize(5L))
    val encodingHandler = new EncodingHandler(contentEncodingRepository)
      .setNext(this)

    val builder = Undertow.builder()
      // TODO: test if we can re-enable this as it was causing problems
//      .setServerOption(UndertowOptions.ENABLE_HTTP2, java.lang.Boolean.TRUE)
      .setHandler(encodingHandler)
    server.config.listeners.foreach {
      case HttpServerListener(host, port, enabled) => if (enabled) {
        builder.addHttpListener(port, host)
      }
      case HttpsServerListener(host, port, keyStore, enabled) => if (enabled) {
        val sslContext = SSLUtil.createSSLContext(keyStore.location, keyStore.password)
        builder.addHttpsListener(port, host, sslContext)
      }
    }
    val u = builder.build()
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

  private val formParserBuilder = FormParserFactory.builder()

  override def handleRequest(exchange: HttpServerExchange): Unit = server.errorSupport {
    if (exchange.getRequestContentLength > 0L && exchange.getRequestHeaders.getFirst("Content-Type").startsWith("multipart/form-data")) {
      if (exchange.isInIoThread) {
        exchange.dispatch(this)
      } else {
        exchange.startBlocking()
        val formDataParser = formParserBuilder.build().createParser(exchange)
        formDataParser.parseBlocking()
        requestHandler.handleRequest(exchange)
      }
    } else {
      requestHandler.handleRequest(exchange)
    }
  }

  private val requestHandler = new UndertowHttpHandler {
    override def handleRequest(exchange: HttpServerExchange): Unit = {
      val request = UndertowServerImplementation.request(exchange)
      val connection = new HttpConnection(server, request)
      server.handle(connection)
      UndertowServerImplementation.response(server, connection, exchange)
    }
  }
}

object UndertowServerImplementation {
  def parseHeaders(headerMap: HeaderMap): Headers = Headers(headerMap.asScala.map { hv =>
    hv.getHeaderName.toString -> hv.asScala.toList
  }.toMap)

  def request(exchange: HttpServerExchange): HttpRequest = {
    val source = IP(exchange.getSourceAddress.getAddress.getHostAddress)
    val headers = parseHeaders(exchange.getRequestHeaders)
    val content: Option[RequestContent] = if (exchange.getRequestContentLength > 0L) {
      Headers.`Content-Type`.value(headers).getOrElse(ContentType.`text/plain`) match {
        case ContentType.`multipart/form-data` => {
          val formData = exchange.getAttachment(FormDataParser.FORM_DATA)
          val data = formData.asScala.toList.map { key =>
            val entries: List[FormDataEntry] = formData.get(key).asScala.map { entry =>
              val headers = parseHeaders(entry.getHeaders)
              if (entry.isFile) {
                FileEntry(entry.getFileName, entry.getPath.toFile, headers)
              } else {
                StringEntry(entry.getValue, headers)
              }
            }.toList
            FormData(key, entries)
          }
          Some(FormDataContent(data))
        }
        case ct => throw new RuntimeException(s"Only multipart/form-data is supported currently but received $ct")
      }
    } else {
      None
    }

    HttpRequest(
      method = Method(exchange.getRequestMethod.toString),
      source = source,
      url = URL(s"${exchange.getRequestURL}?${exchange.getQueryString}"),
      headers = headers,
      content = content
    )
  }

  def response(server: Server, connection: HttpConnection, exchange: HttpServerExchange): Unit = {
    connection.webSocketSupport match {
      case Some(webSocketListener) => handleWebSocket(server, connection, webSocketListener, exchange)
      case None => connection.proxySupport match {
        case Some(proxyHandler) => {
          proxyHandler.proxy(connection) match {
            case Some(destination) => handleProxy(server, connection, exchange, destination, proxyHandler.keyStore)
            case None => handleStandard(server, connection, exchange)
          }
        }
        case None => handleStandard(server, connection, exchange)
      }
    }
  }

  private def handleWebSocket(server: Server,
                              httpConnection: HttpConnection,
                              connection: Connection,
                              exchange: HttpServerExchange): Unit = {
    val handler = Handlers.websocket(new WebSocketConnectionCallback {
      override def onConnect(exchange: WebSocketHttpExchange, channel: WebSocketChannel): Unit = {
        // Handle sending messages
        connection.send.text.attach { message =>
          WebSockets.sendText(message, channel, null)
        }
        connection.send.binary.attach { message =>
          WebSockets.sendBinary(message, channel, null)
        }
        connection.send.close.attach { _ =>
          channel.sendClose()
        }

        // Handle receiving messages
        channel.getReceiveSetter.set(new AbstractReceiveListener {
          override def onFullTextMessage(channel: WebSocketChannel, message: BufferedTextMessage): Unit = {
            val data = message.getData
            connection.receive.text := data
            super.onFullTextMessage(channel, message)
          }

          override def onFullBinaryMessage(channel: WebSocketChannel, message: BufferedBinaryMessage): Unit = {
            connection.receive.binary := message.getData.getResource
            super.onFullBinaryMessage(channel, message)
          }

          override def onError(channel: WebSocketChannel, error: Throwable): Unit = {
            connection.error := error
            super.onError(channel, error)
          }

          override def onFullCloseMessage(channel: WebSocketChannel, message: BufferedBinaryMessage): Unit = {
            connection.receive.close := Unit
            super.onFullCloseMessage(channel, message)
          }
        })
        channel.resumeReceives()
        connection._connected := true
      }
    })
    handler.handleRequest(exchange)
  }

  private def handleProxy(server: Server,
                          connection: HttpConnection,
                          exchange: HttpServerExchange,
                          destination: URL,
                          keyStore: Option[KeyStore]): Unit = {
    val proxyClient = new LoadBalancingProxyClient
    val ssl = keyStore.map { ks =>
      val sslContext = SSLUtil.createSSLContext(ks.location, ks.password)
      new UndertowXnioSsl(Xnio.getInstance(), OptionMap.EMPTY, sslContext)
    }
    val uri = new URI(destination.copy(path = Path.empty, parameters = Parameters.empty, fragment = None).toString)
    proxyClient.addHost(uri, ssl.orNull)
    val proxyHandler = Handlers.proxyHandler(proxyClient)
    exchange.setRequestPath(destination.path.encoded)
    exchange.setRequestURI(destination.path.encoded)
    proxyHandler.handleRequest(exchange)
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
          case StringContent(s, contentType, lastModified) => {
            exchange.getResponseSender.send(s, new IoCallback {
              override def onComplete(exchange: HttpServerExchange, sender: Sender): Unit = {
                sender.close()
              }

              override def onException(exchange: HttpServerExchange, sender: Sender, exception: IOException): Unit = {
                sender.close()
                server.error(exception)
              }
            })
          }
          case fc: FileContent => ResourceServer.serve(exchange, fc)
          /*case FileContent(file, contentType) => {
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
          }*/
          case URLContent(url, contentType) => {
            val resource = new URLResource(url, "")
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