package io.youi.server

import java.io.IOException
import java.net.URI
import java.nio.ByteBuffer

import reactify.Var
import io.undertow.protocols.ssl.UndertowXnioSsl
import io.undertow.server.DefaultByteBufferPool
import io.undertow.util.Headers
import io.undertow.websockets.client.WebSocketClient.ConnectionBuilder
import io.undertow.websockets.client.{WebSocketClientNegotiation, WebSocketClient => UndertowWebSocketClient}
import io.undertow.websockets.core.{AbstractReceiveListener, BufferedTextMessage, WebSocketCallback, WebSocketChannel, WebSockets}
import io.youi.http.Connection
import io.youi.net.URL
import io.youi.server.util.SSLUtil
import io.youi.util.Time
import org.xnio.{IoFuture, OptionMap, Options, Xnio}

import scala.collection.JavaConverters._
import scala.concurrent.duration._
import scribe.Execution.global

import scala.concurrent.{Future, Promise}

class WebSocketClient(url: URL,
                      keyStore: Option[KeyStore] = None,
                      autoReconnect: Boolean = true,
                      reconnectDelay: FiniteDuration = 5.seconds,
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
    .set(Options.KEEP_ALIVE, true)
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

  private var backlog = List.empty[AnyRef]

  def connect(): Future[Unit] = if (_channel.get.isEmpty) {
    val promise = Promise[Unit]
    connectionBuilder.connect().addNotifier(new IoFuture.HandlingNotifier[WebSocketChannel, Any] {
      override def handleDone(data: WebSocketChannel, attachment: Any): Unit = {
        _channel := Some(data)
        channel.resumeReceives()

        // Receive messages
        channel.getReceiveSetter.set(new AbstractReceiveListener {
          override def onFullTextMessage(channel: WebSocketChannel, message: BufferedTextMessage): Unit = {
            val data = message.getData
            receive.text := data
          }

          override def onError(channel: WebSocketChannel, error: Throwable): Unit = {
            super.onError(channel, error)
            disconnect()
          }
        })

        // Send messages
        send.text.attach { message =>
          checkBacklog()
          sendMessage(message)
        }
        send.binary.attach { message =>
          checkBacklog()
          sendMessage(message)
        }
        _connected := true
        scribe.info(s"Connected to $url successfully")

        checkBacklog()

        promise.success(())
      }

      override def handleFailed(exception: IOException, attachment: Any): Unit = {
        _channel := None
        if (autoReconnect) {
          scribe.warn(s"Connection closed or unable to connect to $url (${exception.getMessage}). Trying again in ${reconnectDelay.toSeconds} seconds...")
          Time.delay(reconnectDelay).foreach(_ => connect())
        } else {
          scribe.warn("Connection closed or unable to connect.")
        }
      }
    }, None.orNull)
    promise.future
  } else {
    Future.successful(())
  }

  def disconnect(): Unit = if (connected()) {
    channel.close()
    _connected := false
  }

  def dispose(): Unit = {
    disconnect()
    worker.shutdown()
  }

  private def checkBacklog(): Unit = {
    if (backlog.nonEmpty) {
      synchronized {
        backlog.foreach {
          case text: String => sendMessage(text)
          case binary: ByteBuffer => sendMessage(binary)
        }
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
        disconnect()
      }
    })
  }

  private def sendMessage(message: ByteBuffer): Unit = {
    WebSockets.sendBinary(message, channel, new WebSocketCallback[Void] {
      override def complete(channel: WebSocketChannel, context: Void): Unit = {
        // Successfully sent
      }

      override def onError(channel: WebSocketChannel, context: Void, throwable: Throwable): Unit = WebSocketClient.this synchronized {
        backlog = message :: backlog
        disconnect()
      }
    })
  }

  connected.attach { b =>
    if (!b) {
      _channel := None

      if (autoReconnect) {
        connect()
      }
    }
  }
}
