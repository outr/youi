package io.youi.server

import com.outr.reactify._
import com.outr.scribe._
import io.youi.http.{HttpConnection, Status}
import io.youi.server.handler.{HttpHandler, HttpHandlerBuilder}
import io.youi.server.session.SessionStore

trait Server extends HttpHandler {
  private val _connection = new ThreadLocal[Option[HttpConnection]] {
    override def initialValue(): Option[HttpConnection] = None
  }

  def connectionOption: Option[HttpConnection] = _connection.get()
  def connection: HttpConnection = connectionOption.getOrElse(throw new RuntimeException("No connection defined on the current thread."))
  def withConnection[R](connection: HttpConnection)(f: => R): R = {
    val previous = _connection.get()
    _connection.set(Some(connection))
    try {
      f
    } finally {
      _connection.set(previous)
    }
  }

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
    logger.info(s"Server started on ${config.host()}:${config.port()}")
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

  def dispose(): Unit = {
    SessionStore.dispose()
  }

  override def handle(connection: HttpConnection): Unit = withConnection(connection) {
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
