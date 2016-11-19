package io.youi.server

import com.outr.props._
import com.outr.scribe.Logging
import io.youi.http.{HttpConnection, HttpRequest, HttpResponse, Status}
import io.youi.net.URLMatcher
import io.youi.server.handler.HttpHandler

trait Server extends HttpHandler with Logging {
  val config = new ServerConfig(this)

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
    def add(matcher: URLMatcher)(handler: HttpHandler): HttpHandler = {
      val h = new HttpHandler {
        override def handle(connection: HttpConnection): Unit = {
          if (matcher.matches(connection.request.url)) {
            handler.handle(connection)
          }
        }
      }
      this += h
      h
    }

    def apply(): List[HttpHandler] = entries
  }

  protected val implementation: ServerImplementation

  def isRunning: Boolean = implementation.isRunning

  def start(): Unit = synchronized {
    implementation.start()
  }

  def stop(): Unit = synchronized {
    implementation.stop()
  }

  def restart(): Unit = synchronized {
    stop()
    start()
  }

  def error(t: Throwable): Unit = logger.error(t)

  def errorSupport[R](f: => R): R = try {
    f
  } catch {
    case t: Throwable => {
      error(t)
      throw t
    }
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
