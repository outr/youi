package io.youi.server

import java.util.concurrent.atomic.AtomicBoolean

import reactify._
import io.youi.{ErrorSupport, ItemContainer}
import io.youi.http.{HttpConnection, HttpStatus, ProxyHandler}
import io.youi.server.handler.{HttpHandler, HttpHandlerBuilder}
import io.youi.server.session.SessionStore
import profig.{Profig, ProfigPath}

import scala.annotation.tailrec
import scala.concurrent.Future
import scribe.Execution.global

trait Server extends HttpHandler with ErrorSupport {
  private val initialized = new AtomicBoolean(false)

  val config = new ServerConfig(this)

  val handler = HttpHandlerBuilder(this)

  object proxies extends ItemContainer[ProxyHandler]
  object handlers extends ItemContainer[HttpHandler]

  /**
    * The error handler if an error is thrown. This is used automatically when an HttpHandler fires a Throwable but
    * can be explicitly used for more specific errors. The error handler is responsible for applying an existing
    * status on the HttpResponse or setting one if the status is a non-error.
    *
    * Defaults to DefaultErrorHandler
    */
  val errorHandler: Var[ErrorHandler] = Var(DefaultErrorHandler)

  protected lazy val implementation: ServerImplementation = {
    Server.config("implementation").opt[String] match {
      case Some(className) => {
        scribe.info(s"Using server implementation: $className...")
        import scala.reflect.runtime._

        val clazz = Class.forName(className)
        val rootMirror = universe.runtimeMirror(Thread.currentThread().getContextClassLoader)
        val moduleSymbol = rootMirror.moduleSymbol(clazz)
        val moduleMirror = rootMirror.reflectModule(moduleSymbol)
        val creator = moduleMirror.instance.asInstanceOf[ServerImplementationCreator]
        creator.create(this)
      }
      case None => throw new RuntimeException("No server implementation available.")
    }
  }

  implicit class ServerHttpHandler(handler: HttpHandler) {
    def add(): Unit = handlers += handler
  }

  def isInitialized: Boolean = initialized.get()
  def isRunning: Boolean = isInitialized && implementation.isRunning

  /**
    * Init is called on start(), but only the first time. If the server is restarted it is not invoked again.
    */
  protected def init(): Future[Unit] = Future.successful(())

  def start(): Future[Unit] = {
    val shouldInit = initialized.compareAndSet(false, true)
    val future = if (shouldInit) {
      init()
    } else {
      Future.successful(())
    }

    future.map { _ =>
      implementation.start()
      scribe.info(s"Server started on ${config.enabledListeners.mkString(", ")}")
    }
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

  override final def handle(connection: HttpConnection): Unit = try {
    handleInternal(connection)
  } catch {
    case t: Throwable => {
      error(t)
      errorHandler.get.handle(connection, Some(t))
    }
  }

  protected def handleInternal(connection: HttpConnection): Unit = {
    handleRecursive(connection, handlers())

    // NotFound handling
    if (connection.response.content.isEmpty && connection.response.status == HttpStatus.OK) {
      connection.update { response =>
        response.copy(status = HttpStatus.NotFound)
      }
      errorHandler.get.handle(connection, None)
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

object Server {
  def config: ProfigPath = Profig("youi.server")
}