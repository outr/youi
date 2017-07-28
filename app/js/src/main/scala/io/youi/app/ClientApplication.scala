package io.youi.app

import io.youi.ajax.AjaxRequest
import io.youi.app.screen.ScreenManager
import io.youi.{History, JavaScriptError}
import io.youi.app.sourceMap.ErrorTrace
import org.scalajs.dom._
import io.youi.dom._
import io.youi.net.URL

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js

trait ClientApplication extends YouIApplication with ScreenManager {
  addScript("/source-map.min.js")

  // Configure communication end-points
  private var configuredConnectivity: Map[ApplicationConnectivity, ClientConnectivity] = Map.empty

  def clientConnectivity(connectivity: ApplicationConnectivity): ClientConnectivity = configuredConnectivity(connectivity)

  private val errorFunction: js.Function5[String, String, Int, Int, Throwable, Unit] = (message: String, source: String, line: Int, column: Int, throwable: Throwable) => {
    ErrorTrace.toError(message, source, line, column, Option(throwable)).map(ClientApplication.sendError)
    ()
  }

  if (logJavaScriptErrors) {
    js.Dynamic.global.window.onerror = errorFunction
    scribe.Logger.root.addHandler(ErrorTrace)
  }

  connectivityEntries.attachAndFire { entries =>
    entries.foreach { connectivity =>
      if (!configuredConnectivity.contains(connectivity)) {
        configuredConnectivity += connectivity -> new ClientConnectivity(connectivity, this)
      }
    }
  }

  def autoReload: Boolean = true

  override def cached(url: URL): String = url.asPath()
}

object ClientApplication {
  def sendError(throwable: Throwable): Future[XMLHttpRequest] = {
    ErrorTrace.toError(throwable).flatMap(sendError)
  }
  def sendError(error: JavaScriptError): Future[XMLHttpRequest] = {
    val formData = new FormData
    val json = upickle.default.write[JavaScriptError](error)
    formData.append("json", json)
    val request = new AjaxRequest(History.url().replacePathAndParams("/clientError"), data = Some(formData))
    request.send()
  }
  def sendError(event: ErrorEvent): Future[XMLHttpRequest] = {
    ErrorTrace.toError(event).flatMap(sendError)
  }
}