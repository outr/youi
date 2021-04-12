package io.youi.app

import fabric.parse.Json
import io.youi.ajax.AjaxRequest
import io.youi.app.screen.ScreenManager
import io.youi.app.sourceMap.ErrorTrace
import io.youi.net._
import io.youi.storage.{LocalStorage, UniversalStorage}
import io.youi.{History, JavaScriptError, JavaScriptLog}
import org.scalajs.dom.{ErrorEvent, FormData, XMLHttpRequest, window}
import fabric.rw._
import scribe.output.LogOutput
import scribe.output.format.OutputFormat
import scribe.writer.Writer
import scribe.{Level, LogRecord}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.|

trait ClientApplication extends YouIApplication with ScreenManager {
  ClientApplication.instance = this

  def baseURL: Future[URL] = Future.successful(URL(window.location.href).withPath(path"/").clearParams().withoutFragment())

  override def isClient: Boolean = true

  override def isServer: Boolean = false

  private val errorFunction: js.Function5[String, String, Int, Int, Throwable | js.Error, Unit] = (message: String, source: String, line: Int, column: Int, err: Throwable | js.Error) => {
    err match {
      case null => ErrorTrace.toError(message, source, line, column, None).map(ClientApplication.sendError)
      case t: Throwable => ErrorTrace.toError(message, source, line, column, Some(t)).map(ClientApplication.sendError)
      case e: js.Error => ErrorTrace.toError(message, source, line, column, Some(js.JavaScriptException(e))).map(ClientApplication.sendError)
    }
    ()
  }

  if (logJavaScriptErrors) {
    js.Dynamic.global.window.onerror = errorFunction
    scribe.Logger.root.withHandler(writer = ErrorTrace).replace()
  }

  // Client-side management and caching of URL-based session id
  UniversalStorage.string.get("sessionId").map {
    case Some(sessionId) => if (History.url().param("sessionId").contains(sessionId)) {
      // Already set, nothing needed
    } else {
      // Redirect to stored session id
      val url = History.url().withParam("sessionId", sessionId, append = false)
      History.set(url)
    }
    case None => History.url.param("sessionId").foreach { sessionId =>
      // Store the session id in local storage
      UniversalStorage("sessionId") = sessionId
    }
  }

  override def cached(url: URL): String = url.asPath()
}

object ClientApplication {
  def logWriter(baseURL: => URL = History.url(),
                maximumBytes: Long = -1L,
                maximumRecords: Int = -1,
                maximumErrors: Int = -1): Writer = new Writer {
    private var bytesWritten = 0L
    private var recordsWritten = 0
    private var errorsWritten = 0
    private var enabled = true

    override def write[M](record: LogRecord[M], output: LogOutput, outputFormat: OutputFormat): Unit = if (enabled) {
      val text = output.plainText
      bytesWritten += text.length
      recordsWritten += 1
      if (record.level >= Level.Error) errorsWritten += 1
      sendLog(baseURL, JavaScriptLog(text))
      if (maximumBytes != -1L && bytesWritten >= maximumBytes) {
        enabled = false
      } else if (maximumRecords != -1 && recordsWritten >= maximumRecords) {
        enabled = false
      } else if (maximumErrors != -1 && errorsWritten >= maximumErrors) {
        enabled = false
      }
    }
  }

  private var instance: ClientApplication = _

  def sendError(throwable: Throwable): Future[XMLHttpRequest] = {
    ErrorTrace.toError(throwable).flatMap(sendError)
  }

  def sendError(error: JavaScriptError): Future[XMLHttpRequest] = {
    val formData = new FormData
    val jsonString = Json.format(error.toValue)
    formData.append("error", jsonString)
    val request = new AjaxRequest(History.url().replacePathAndParams(instance.logPath), data = Some(formData))
    request.send()
  }

  def sendError(event: ErrorEvent): Future[XMLHttpRequest] = {
    ErrorTrace.toError(event).flatMap(sendError)
  }

  def sendLog(baseURL: URL, log: JavaScriptLog): Future[XMLHttpRequest] = {
    val formData = new FormData
    val jsonString = Json.format(log.toValue)
    formData.append("message", jsonString)
    val request = new AjaxRequest(
      url = baseURL.replacePathAndParams(instance.logPath),
      data = Some(formData),
      withCredentials = false
    )
    request.send()
  }
}