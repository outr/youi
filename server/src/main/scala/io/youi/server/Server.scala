package io.youi.server

import com.outr.reactify._
import io.youi.ErrorSupport
import io.youi.http.{HttpConnection, Status}
import io.youi.server.handler.{HttpHandler, HttpHandlerBuilder}
import io.youi.server.session.SessionStore

trait Server extends HttpHandler with ErrorSupport {
  val config = new ServerConfig(this)

  val handler = HttpHandlerBuilder(this)
  object handlers {
    /**
      * The error handler if an error is thrown. This is used automatically when an HttpHandler fires a Throwable but
      * can be explicitly used for more specific errors. The error handler is responsible for applying an existing
      * status on the HttpResponse or setting one if the status is a non-error.
      *
      * Defaults to DefaultErrorHandler
      */
    val error: Var[ErrorHandler] = Var(DefaultErrorHandler)

    private var entries = List.empty[HttpHandler]

    def +=(handler: HttpHandler): Unit = synchronized {
      entries = (handler :: entries).sorted
    }
    def -=(handler: HttpHandler): Unit = synchronized {
      entries = entries.filterNot(_ eq handler)
    }

    def apply(): List[HttpHandler] = entries
  }

  protected val implementation: ServerImplementation

  def isRunning: Boolean = implementation.isRunning

  def start(): Unit = synchronized {
    implementation.start()
    scribe.info(s"Server started on ${config.listeners().mkString(", ")}")
  }

  def stop(): Unit = synchronized {
    implementation.stop()
  }

  def restart(): Unit = synchronized {
    stop()
    start()
  }

  def dispose(): Unit = {
    SessionStore.dispose()
  }

  override def handle(connection: HttpConnection): Unit = {
    try {
      handlers().foreach(_.handle(connection))
      if (connection.response.content.isEmpty && connection.response.status == Status.OK) {
        connection.update { response =>
          response.copy(status = Status.NotFound)
        }
        handlers.error.get.handle(connection, None)
      }
    } catch {
      case t: Throwable => {
        error(t)
        handlers.error.get.handle(connection, Some(t))
      }
    }
  }
}
