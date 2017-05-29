package io.youi.server

import reactify._
import io.youi.{ErrorSupport, ItemContainer}
import io.youi.http.{HttpConnection, Status}
import io.youi.server.handler.{HttpHandler, HttpHandlerBuilder}
import io.youi.server.session.SessionStore

import scala.annotation.tailrec

trait Server extends HttpHandler with ErrorSupport {
  private var initialized = false

  val config = new ServerConfig(this)

  val handler = HttpHandlerBuilder(this)

  object handlers extends ItemContainer[HttpHandler]

  /**
    * The error handler if an error is thrown. This is used automatically when an HttpHandler fires a Throwable but
    * can be explicitly used for more specific errors. The error handler is responsible for applying an existing
    * status on the HttpResponse or setting one if the status is a non-error.
    *
    * Defaults to DefaultErrorHandler
    */
  val errorHandler: Var[ErrorHandler] = Var(DefaultErrorHandler)

  protected val implementation: ServerImplementation

  def isInitialized: Boolean = initialized
  def isRunning: Boolean = implementation.isRunning

  /**
    * Init is called on start(), but only the first time. If the server is restarted it is not invoked again.
    */
  protected def init(): Unit = {
  }

  def start(): Unit = synchronized {
    if (!initialized) {
      init()
      initialized = true
    }

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
    stop()
    SessionStore.dispose()
  }

  override def handle(connection: HttpConnection): Unit = {
    try {
      handleRecursive(connection, handlers())

      // NotFound handling
      if (connection.response.content.isEmpty && connection.response.status == Status.OK) {
        connection.update { response =>
          response.copy(status = Status.NotFound)
        }
        errorHandler.get.handle(connection, None)
      }
    } catch {
      case t: Throwable => {
        error(t)
        errorHandler.get.handle(connection, Some(t))
      }
    }
  }

  @tailrec
  private def handleRecursive(connection: HttpConnection, handlers: List[HttpHandler]): Unit = {
    if (connection.isFinished || handlers.isEmpty) {
      // Finished
    } else {
      val handler = handlers.head
      handler.handle(connection)

      handleRecursive(connection, handlers.tail)
    }
  }
}
