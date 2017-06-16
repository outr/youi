package io.youi.server

import java.net.URI

import reactify.Var
import io.undertow.protocols.ssl.UndertowXnioSsl
import io.undertow.server.DefaultByteBufferPool
import io.undertow.util.Headers
import io.undertow.websockets.client.WebSocketClient.ConnectionBuilder
import io.undertow.websockets.client.{WebSocketClient => UndertowWebSocketClient, WebSocketClientNegotiation}
import io.undertow.websockets.core.{AbstractReceiveListener, BufferedTextMessage, WebSocketCallback, WebSocketChannel, WebSockets}
import io.youi.http.Connection
import io.youi.net.URL
import io.youi.server.util.SSLUtil
import org.xnio.{OptionMap, Options, Xnio}

import scala.collection.JavaConverters._

// TODO: support auto-reconnect
class WebSocketClient(url: URL,
                      keyStore: Option[KeyStore] = None,
                      autoReconnect: Boolean = true,
                      directBuffer: Boolean = false,
                      bufferSize: Int = 2048,
                      workerThreads: Int = 2,
                      highWater: Int = 1000000,
                      lowWater: Int = 1000000,
                      coreThreads: Int = 30,
                      maxThreads: Int = 30,
                      noDelay: Boolean = true,
                      buffered: Boolean = true,
                      authorization: => Option[String] = None) extends Connection {
  private lazy val worker = Xnio.getInstance().createWorker(OptionMap.builder()
    .set(Options.WORKER_IO_THREADS, workerThreads)
    .set(Options.CONNECTION_HIGH_WATER, highWater)
    .set(Options.CONNECTION_LOW_WATER, lowWater)
    .set(Options.WORKER_TASK_CORE_THREADS, coreThreads)
    .set(Options.WORKER_TASK_MAX_THREADS, maxThreads)
    .set(Options.TCP_NODELAY, noDelay)
    .set(Options.CORK, buffered)
    .getMap
  )
  private lazy val bufferPool = new DefaultByteBufferPool(directBuffer, bufferSize)
  private lazy val connectionBuilder: ConnectionBuilder = {
    val builder = UndertowWebSocketClient.connectionBuilder(worker, bufferPool, new URI(url.toString))
      .setClientNegotiation(new WebSocketClientNegotiation(null, null) {
        override def beforeRequest(headers: java.util.Map[String, java.util.List[String]]): Unit = {
          authorization.foreach { auth =>
            headers.put(Headers.AUTHORIZATION_STRING, List(auth).asJava)
          }
          headers.put(Headers.UPGRADE_STRING, List("websocket").asJava)
          headers.put(Headers.CONNECTION_STRING, List("Upgrade").asJava)
        }
      })
    keyStore.foreach { ks =>
      val sslContext = SSLUtil.createSSLContext(ks.location, ks.password)
      builder.setSsl(new UndertowXnioSsl(Xnio.getInstance(), OptionMap.EMPTY, sslContext))
    }
    builder
  }

  private val _channel = Var[Option[WebSocketChannel]](None)
  def channel: WebSocketChannel = _channel.get.getOrElse(throw new RuntimeException("No connection has been established."))

  private var backlog = List.empty[String]

  def connect(): Unit = if (_channel.get.isEmpty) {
    _channel.static(Some(connectionBuilder.connect().get()))

    channel.resumeReceives()

    // Receive messages
    channel.getReceiveSetter.set(new AbstractReceiveListener {
      override def onFullTextMessage(channel: WebSocketChannel, message: BufferedTextMessage): Unit = {
        val data = message.getData
        receive.text := data
      }

      override def onError(channel: WebSocketChannel, error: Throwable): Unit = {
        super.onError(channel, error)
        disconnected()
      }
    })

    // Send messages
    send.text.attach { message =>
      checkBacklog()
      sendMessage(message)
    }
    _connected := true

    checkBacklog()
  }

  def disconnect(): Unit = {
    channel.close()
  }

  def dispose(): Unit = {
    if (connected()) {
      disconnect()
    }
    worker.shutdown()
  }

  private def checkBacklog(): Unit = {
    if (backlog.nonEmpty) {
      synchronized {
        backlog.foreach(sendMessage)
        backlog = Nil
      }
    }
  }

  private def sendMessage(message: String): Unit = {
    WebSockets.sendText(message, channel, new WebSocketCallback[Void] {
      override def complete(channel: WebSocketChannel, context: Void): Unit = {
        // Successfully sent
      }

      override def onError(channel: WebSocketChannel, context: Void, throwable: Throwable): Unit = WebSocketClient.this synchronized {
        backlog = message :: backlog
      }
    })
  }

  private def disconnected(): Unit = {
    _channel := None

    if (autoReconnect) {
      connect()
    }
  }
}
