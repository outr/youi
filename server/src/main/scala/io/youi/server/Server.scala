package io.youi.server

import cats.effect.{ExitCode, IO, IOApp}

import java.util.concurrent.atomic.AtomicBoolean
import io.youi.http.{HttpConnection, HttpStatus, ProxyHandler}
import io.youi.server.handler.{HttpHandler, HttpHandlerBuilder}
import io.youi.{ErrorSupport, ItemContainer}
import moduload.Moduload
import profig._
import reactify._
import scala.concurrent.duration._

trait Server extends HttpHandler with ErrorSupport with IOApp {
  private val initialized = new AtomicBoolean(false)

  val config = new ServerConfig(this)

  val handler: HttpHandlerBuilder = HttpHandlerBuilder(this)

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
    Moduload.load()
    Option(Server.implementationCreator) match {
      case Some(creator) => creator.create(this)
      case None => throw new RuntimeException("No server implementation found")
    }
  }

  implicit class ServerHttpHandler(handler: HttpHandler) {
    def add(): Unit = handlers += handler
  }

  def isInitialized: Boolean = initialized.get()
  def isRunning: Boolean = isInitialized && implementation.isRunning

  def initialize(): IO[Unit] = {
    val shouldInit = initialized.compareAndSet(false, true)
    if (shouldInit) {
      init()
    } else {
      IO.unit
    }
  }

  /**
    * Init is called on start(), but only the first time. If the server is restarted it is not invoked again.
    */
  protected def init(): IO[Unit] = IO.unit

  def start(): IO[Unit] = initialize().map { _ =>
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

  def whileRunning(delay: FiniteDuration = 1.second): IO[Unit] = if (isRunning) {
    IO.sleep(delay).flatMap(_ => whileRunning(delay))
  } else {
    IO.unit
  }

  def dispose(): Unit = stop()

  override final def handle(connection: HttpConnection): IO[HttpConnection] = handleInternal(connection).handleErrorWith { throwable =>
    error(throwable)
    errorHandler.get.handle(connection, Some(throwable))
  }

  protected def handleInternal(connection: HttpConnection): IO[HttpConnection] = {
    handleRecursive(connection, handlers()).flatMap { updated =>
      // NotFound handling
      if (updated.response.content.isEmpty && updated.response.status == HttpStatus.OK) {
        val notFound = updated.modify { response =>
          response.copy(status = HttpStatus.NotFound)
        }
        errorHandler.get.handle(notFound, None)
      } else {
        IO.pure(updated)
      }
    }
  }

  private def handleRecursive(connection: HttpConnection, handlers: List[HttpHandler]): IO[HttpConnection] = {
    if (connection.finished || handlers.isEmpty) {
      IO.pure(connection)     // Finished
    } else {
      val handler = handlers.head
      handler.handle(connection).flatMap { updated =>
        handleRecursive(updated, handlers.tail)
      }
    }
  }

  override def run(args: List[String]): IO[ExitCode] = IO.unit.flatMap { _ =>
    Profig.initConfiguration()
    Profig.merge(args.toSeq)

    for {
      _ <- start().handleError { throwable =>
        scribe.error("Error during application startup", throwable)
        dispose()
        sys.exit(1)
      }
      _ <- whileRunning()
    } yield {
      scribe.info("Server terminated successfully.")
      ExitCode.Success
    }
  }
}

object Server {
  var implementationCreator: ServerImplementationCreator = _

  def config: ProfigPath = Profig("youi.server")
}