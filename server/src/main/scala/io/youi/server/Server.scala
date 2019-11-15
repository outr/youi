package io.youi.server

import java.util.concurrent.atomic.AtomicBoolean

import reactify._
import io.youi.{ErrorSupport, ItemContainer}
import io.youi.http.{HttpConnection, HttpStatus, ProxyHandler}
import io.youi.server.handler.{HttpHandler, HttpHandlerBuilder}
import profig.{Profig, ProfigPath}

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

  def initialize(): Future[Unit] = {
    val shouldInit = initialized.compareAndSet(false, true)
    if (shouldInit) {
      init()
    } else {
      Future.successful(())
    }
  }

  /**
    * Init is called on start(), but only the first time. If the server is restarted it is not invoked again.
    */
  protected def init(): Future[Unit] = Future.successful(())

  def start(): Future[Unit] = initialize().map { _ =>
    implementation.start()
    scribe.info(s"Server started on ${config.enabledListeners.mkString(", ")}")
  }

  def stop(): Unit = synchronized {
    implementation.stop()
  }

  def restart(): Unit = synchronized {
    stop()
    start()
  }

  def dispose(): Unit = stop()

  override final def handle(connection: HttpConnection): Future[HttpConnection] = handleInternal(connection).recoverWith {
    case t => {
      error(t)
      errorHandler.get.handle(connection, Some(t))
    }
  }

  protected def handleInternal(connection: HttpConnection): Future[HttpConnection] = {
    handleRecursive(connection, handlers()).flatMap { updated =>
      // NotFound handling
      if (updated.response.content.isEmpty && updated.response.status == HttpStatus.OK) {
        val notFound = updated.modify { response =>
          response.copy(status = HttpStatus.NotFound)
        }
        errorHandler.get.handle(notFound, None)
      } else {
        Future.successful(updated)
      }
    }
  }

  private def handleRecursive(connection: HttpConnection, handlers: List[HttpHandler]): Future[HttpConnection] = {
    if (connection.finished || handlers.isEmpty) {
      Future.successful(connection)     // Finished
    } else {
      val handler = handlers.head
      handler.handle(connection).flatMap { updated =>
        handleRecursive(updated, handlers.tail)
      }
    }
  }
}

object Server {
  def config: ProfigPath = Profig("youi.server")
}