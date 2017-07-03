package io.youi.app

import java.io.File

import akka.actor.{ActorSystem, Cancellable}
import io.youi.http._
import io.youi.net.URL
import io.youi.server.Server
import io.youi.server.handler.HttpHandler
import io.youi.{JavaScriptError, http}
import net.sf.uadetector.UserAgentType
import net.sf.uadetector.service.UADetectorServiceFactory
import org.powerscala.io._
import reactify.{Channel, Var}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

trait ServerApplication extends YouIApplication with Server {
  private lazy val system = ActorSystem("ServerApplication")

  val connected: Channel[Connection] = Channel[Connection]
  val disconnected: Channel[Connection] = Channel[Connection]
  lazy val cacheDirectory: Var[File] = Var(new File(System.getProperty("user.home"), ".cache"))

  private var configuredEndPoints = Set.empty[ApplicationConnectivity]
  private lazy val userAgentParser = UADetectorServiceFactory.getResourceModuleParser

  connectivityEntries.attachAndFire { entries =>
    ServerApplication.this.synchronized {
      entries.foreach { appComm =>
        if (!configuredEndPoints.contains(appComm)) {
          val appCommHandler = new ServerConnectionHandler(appComm)
          handler.matcher(path.exact(appComm.path)).wrap(appCommHandler)
          configuredEndPoints += appComm
        }
      }
    }
  }
  handler.matcher(path.exact("/source-map.min.js")).resource(Content.classPath("source-map.min.js"))
  if (logJavaScriptErrors) {
    handler.matcher(path.exact("/clientError")).handle { httpConnection =>
      val content = httpConnection.request.content
      content match {
        case Some(requestContent) => requestContent match {
          case formData: FormDataContent => {
            val json = formData.string("json").value
            val jsError = upickle.default.read[JavaScriptError](json)

            val userAgentString = Headers.Request.`User-Agent`.value(httpConnection.request.headers).getOrElse("")
            val userAgent = userAgentParser.parse(userAgentString)

            val exception = new JavaScriptException(
              error = jsError,
              userAgent = userAgent,
              ip = httpConnection.request.source,
              request = httpConnection.request,
              info = errorInfo(jsError, httpConnection)
            )
            if (logJavaScriptException(exception)) {
              error(exception)

            }
          }
          case otherContent => scribe.error(s"Unsupported content type: $otherContent (${otherContent.getClass.getName})")
        }
        case None => // Ignore
      }

      httpConnection.update(_.withContent(Content.empty))
    }
  }

  private val cancellable: Option[Cancellable] = Some(system.scheduler.schedule(30.seconds, 30.seconds) {
    pingClients()
  })

  protected def errorInfo(error: JavaScriptError, httpConnection: HttpConnection): Map[String, String] = Map.empty

  protected def page(page: Page): Page = {
    handlers += page
    page
  }

  protected def logJavaScriptException(exception: JavaScriptException): Boolean = {
    val ua = exception.userAgent
    ua.getType != UserAgentType.ROBOT
  }

  private class ServerConnectionHandler(appComm: ApplicationConnectivity) extends HttpHandler {
    override def handle(httpConnection: HttpConnection): Unit = appComm.activeConnections.synchronized {
      val connection = new Connection
      connection.store.update("httpConnection", httpConnection)
      appComm.activeConnections := (appComm.activeConnections() + connection)
      connected := connection
      connection.connected.attach { b =>
        if (!b) appComm.activeConnections.synchronized {
          appComm.activeConnections := (appComm.activeConnections() - connection)
          disconnected := connection
        }
      }
      connection.receive.text.attach {
        case "PONG" => // Nothing to do, this finishes the workflow
        case _ => // Ignore everything else
      }
      httpConnection.webSocketSupport = connection
    }
  }

  private def pingClients(): Unit = connectivityEntries().foreach { entry =>
    entry.connections().foreach { connection =>
      connection.send.text := "PING"
    }
  }

  // Creates a cached version of the URL and adds an explicit matcher to serve it
  override def cached(url: URL): String = {
    val path = url.asPath()
    val directory = cacheDirectory()
    val file = new File(directory, path)
    file.getParentFile.mkdirs()
    IO.stream(new java.net.URL(url.toString), file)
    val content = Content.file(file)
    handler.matcher(http.path.exact(path)).resource(content)
    path
  }

  def main(args: Array[String]): Unit = {
    start()
  }

  override def dispose(): Unit = {
    super.dispose()

    system.terminate()
  }
}