package io.youi.app

import io.youi.ajax.AjaxRequest
import io.youi.app.screen.ScreenManager
import io.youi.{History, JavaScriptError, JavaScriptLog}
import io.youi.app.sourceMap.ErrorTrace
import org.scalajs.dom._
import io.youi.dom._
import io.youi.net.URL
import profig.JsonUtil
import scribe.LogRecord
import scribe.writer.Writer

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js

trait ClientApplication extends YouIApplication with ScreenManager {
  ClientApplication.instance = this

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
    scribe.Logger.root.withHandler(ErrorTrace).replace()
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
  lazy val logWriter: Writer = new Writer {
    override def write[M](record: LogRecord[M], output: String): Unit = sendLog(JavaScriptLog(output))
  }

  private var instance: ClientApplication = _

  def sendError(throwable: Throwable): Future[XMLHttpRequest] = {
    ErrorTrace.toError(throwable).flatMap(sendError)
  }

  def sendError(error: JavaScriptError): Future[XMLHttpRequest] = {
    val formData = new FormData
    val jsonString = JsonUtil.toJsonString(error)
    formData.append("error", jsonString)
    val request = new AjaxRequest(History.url().replacePathAndParams(instance.logPath), data = Some(formData))
    request.send()
  }

  def sendError(event: ErrorEvent): Future[XMLHttpRequest] = {
    ErrorTrace.toError(event).flatMap(sendError)
  }

  def sendLog(log: JavaScriptLog): Future[XMLHttpRequest] = {
    val formData = new FormData
    val jsonString = JsonUtil.toJsonString(log)
    formData.append("message", jsonString)
    val request = new AjaxRequest(History.url().replacePathAndParams(instance.logPath), data = Some(formData))
    request.send()
  }
}