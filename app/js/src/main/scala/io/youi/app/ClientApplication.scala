package io.youi.app

import reactify.{ChangeListener, Var}
import io.youi.app.screen.ScreenManager
import io.youi.app.stream.StreamURL
import io.youi.http.{Connection, WebSocketUtil}
import io.youi.net.URL
import org.scalajs.dom._
import io.youi.{ErrorSupport, History}
import io.youi.ajax.AjaxRequest
import org.scalajs.dom.ext.AjaxException

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

trait ClientApplication extends YouIApplication with ScreenManager {
  val connection: Connection = new Connection
  val webSocket: Var[Option[WebSocket]] = Var(None)

  // Configure communication end-points
  private var configuredEndPoints = Set.empty[ApplicationCommunication]

  window.addEventListener("error", (evt: ErrorEvent) => {
    error(new JavaScriptError(
      column = evt.colno,
      fileName = evt.filename,
      line = evt.lineno,
      message = evt.message,
      url = History.url().toString,
      userAgent = window.navigator.userAgent,
      appName = window.navigator.appName,
      appVersion = window.navigator.appVersion,
      platform = window.navigator.platform,
      language = window.navigator.language,
      referrer = document.referrer
    ))
  })
  ErrorSupport.error.attach { throwable =>
    val (column, fileName, lineNumber, url, message) = throwable match {
      case error: JavaScriptError => (error.column, error.fileName, error.line, error.url, error.message)
      case _ => {
        val trace = throwable.getStackTrace.head
        (-1, trace.getClassName, trace.getLineNumber, History.url().toString, throwable.getMessage)
      }
    }

    val formData = new FormData
    formData.append("column", column)
    formData.append("fileName", fileName)
    formData.append("lineNumber", lineNumber)
    formData.append("url", History.url().toString)
    formData.append("message", throwable.getMessage)
    formData.append("userAgent", window.navigator.userAgent)
    formData.append("appName", window.navigator.appName)
    formData.append("appVersion", window.navigator.appVersion)
    formData.append("platform", window.navigator.platform)
    formData.append("language", window.navigator.language)
    formData.append("referrer", document.referrer)
    val request = new AjaxRequest(History.url().replacePathAndParams("/clientError"), data = Some(formData))
    request.send()
  }

  if (autoConnect) {
    connect()
  }
  connection.connected.changes(new ChangeListener[Boolean] {
    override def change(oldValue: Boolean, newValue: Boolean): Unit = if (oldValue && !newValue && autoReload) {
      attemptReload()
    }
  })

  def autoConnect: Boolean = true

  def autoReload: Boolean = true

  def connect(): Unit = synchronized {
    communication.attachAndFire { entries =>
      entries.foreach { appComm =>
        if (!configuredEndPoints.contains(appComm)) {
          appComm.activeConnections := Set(connection)
          configuredEndPoints += appComm
        }
      }
    }

    disconnect()
    val protocol = if (window.location.protocol == "https:") {
      "wss"
    } else {
      "ws"
    }
    val comms = communication()
    if (comms.isEmpty) throw new RuntimeException(s"Unable to connect, there are no communication instances.")
    val connectionPath = comms.head.path      // TODO: evaluate supporting more than one in the ClientApplication
    val url = URL(s"$protocol://${window.location.host}$connectionPath")
    webSocket := Some(WebSocketUtil.connect(url, connection))
  }

  def disconnect(): Unit = synchronized {
    webSocket().foreach { ws =>
      if (ws.readyState == WebSocket.OPEN) {
        ws.close()
      }
      webSocket := None
    }
  }

  def close(): Unit = {
    connection.close()
    disconnect()
  }

  private def attemptReload(attempt: Int = 0): Unit = {
    StreamURL.stream(History.url()).onComplete {
      case Success(html) => History.reload(force = true)
      case Failure(exception) => {
        exception match {
          case exc: AjaxException if exc.xhr.status > 0 => History.reload(force = true)
          case _ => {
            val timeout = if (attempt < 10) {
              2500
            } else if (attempt < 25) {
              5000
            } else if (attempt < 100) {
              10000
            } else {
              30000
            }

            window.setTimeout(() => {
              attemptReload(attempt + 1)
            }, timeout)
          }
        }
      }
    }
  }
}