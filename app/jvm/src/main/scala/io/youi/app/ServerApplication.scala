package io.youi.app

import io.youi.{ErrorSupport, JavaScriptError}
import reactify.Channel
import io.youi.http._
import io.youi.server.Server
import io.youi.server.handler.HttpHandler

trait ServerApplication extends YouIApplication with Server {
  val connected: Channel[Connection] = Channel[Connection]
  val disconnected: Channel[Connection] = Channel[Connection]

  private var configuredEndPoints = Set.empty[ApplicationConnectivity]

  ErrorSupport.error.detach(ErrorSupport.defaultHandler)
  ErrorSupport.error.attach(scribe.error(_))

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
    val formData = content.get.asInstanceOf[FormDataContent]
    val json = formData.string("json").value
    val jsError = upickle.default.read[JavaScriptError](json)
    error(new JavaScriptException(jsError))

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