package io.youi.app

import io.youi.{ErrorSupport, JavaScriptError}
import reactify.Channel
import io.youi.http._
import io.youi.server.Server
import io.youi.server.handler.HttpHandler
import net.sf.uadetector.UserAgentType
import net.sf.uadetector.service.UADetectorServiceFactory

trait ServerApplication extends YouIApplication with Server {
  val connected: Channel[Connection] = Channel[Connection]
  val disconnected: Channel[Connection] = Channel[Connection]

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
      httpConnection.webSocketSupport = connection
    }
  }
}