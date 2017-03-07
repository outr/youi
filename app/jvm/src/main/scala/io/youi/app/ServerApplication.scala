package io.youi.app

import reactify.Channel
import io.youi.http._
import io.youi.server.Server
import io.youi.server.handler.HttpHandler

trait ServerApplication extends YouIApplication with Server {
  val connected: Channel[Connection] = Channel[Connection]
  val disconnected: Channel[Connection] = Channel[Connection]

  // Configure communication end-points
  private var configuredEndPoints = Set.empty[ApplicationConnectivity]
  communicationEntries.attachAndFire { entries =>
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
  handler.matcher(path.exact("/clientError")).handle { httpConnection =>
    val content = httpConnection.request.content
    val formData = content.get.asInstanceOf[FormDataContent]
    val column = formData.string("column").value.toInt
    val fileName = formData.string("fileName").value
    val lineNumber = formData.string("lineNumber").value.toInt
    val url = formData.string("url").value
    val message = formData.string("message").value
    val userAgent = formData.string("userAgent").value
    val appName = formData.string("appName").value
    val appVersion = formData.string("appVersion").value
    val platform = formData.string("platform").value
    val language = formData.string("language").value
    val referrer = formData.string("referrer").value
    error(new JavaScriptError(
      column = column,
      fileName = fileName,
      line = lineNumber,
      message = message,
      url = url,
      userAgent = userAgent,
      appName = appName,
      appVersion = appVersion,
      platform = platform,
      language = language,
      referrer = referrer,
      ip = httpConnection.request.source
    ))

    httpConnection.update(_.withContent(Content.empty))
  }

  protected def page(page: Page): Page = {
    handlers += page
    page
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