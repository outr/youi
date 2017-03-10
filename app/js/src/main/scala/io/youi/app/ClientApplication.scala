package io.youi.app

import reactify.{ChangeListener, Var}
import io.youi.app.screen.ScreenManager
import io.youi.app.stream.StreamURL
import io.youi.http.{Connection, WebSocketUtil}
import io.youi.net.URL
import org.scalajs.dom._
import io.youi.{ErrorSupport, History, JavaScriptError}
import io.youi.ajax.AjaxRequest
import io.youi.app.sourceMap.{SourceMapConsumer, SourcePosition}
import org.scalajs.dom._
import io.youi.dom._
import org.scalajs.dom.ext.AjaxException

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.JSON

trait ClientApplication extends YouIApplication with ScreenManager {
  val connection: Connection = new Connection
  val webSocket: Var[Option[WebSocket]] = Var(None)

  private val script = create[html.Script]("script")
  script.src = "/source-map.min.js"
  document.body.appendChild(script)

  private var sourceMaps = Map.empty[String, js.Object]

  // Configure communication end-points
  private var configuredEndPoints = Set.empty[ApplicationConnectivity]

  private def attempt[T](f: => T, default: => T): T = try {
    f
  } catch {
    case _: Throwable => default
  }

  window.addEventListener("error", (evt: ErrorEvent) => {
    error(new JavaScriptError(
      column = attempt(evt.colno, -1),
      fileName = attempt(evt.filename, ""),
      line = attempt(evt.lineno, -1),
      name = "",
      mapped = false,
      message = attempt(evt.message, ""),
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

    val sourcePosition: Future[Option[SourcePosition]] = if (fileName.nonEmpty && lineNumber != -1 && column != -1) {
      StreamURL.stream(URL(s"$fileName.map")).map { jsonString =>
        try {
          val json = JSON.parse(jsonString).asInstanceOf[js.Object]
          sourceMaps += fileName -> json
          val consumer = new SourceMapConsumer(json)
          val position = JSON.parse(
            s"""
               |{
               |   "line": $lineNumber,
               |   "column": $column
               |}
          """.stripMargin).asInstanceOf[js.Object]
          Some(consumer.originalPositionFor(position))
        } catch {
          case t: Throwable => {
            scribe.error(t)
            None
          }
        }
      }
    } else {
      Future.successful(None)
    }

    sourcePosition.onComplete { result =>
      val sp = result match {
        case Success(o) => o
        case Failure(t) => {
          scribe.error(t)
          None
        }
      }

      val formData = new FormData
      formData.append("column", sp.map(_.column).getOrElse(column).toString)
      formData.append("fileName", sp.map(_.source).getOrElse(fileName).toString)
      formData.append("lineNumber", sp.map(_.line).getOrElse(lineNumber).toString)
      formData.append("name", sp.map(_.name).getOrElse("").toString)
      formData.append("mapped", sp.isDefined.toString)
      formData.append("url", History.url().toString)
      formData.append("message", message)
      formData.append("userAgent", window.navigator.userAgent)
      formData.append("appName", window.navigator.appName)
      formData.append("appVersion", window.navigator.appVersion)
      formData.append("platform", window.navigator.platform)
      formData.append("language", window.navigator.language)
      formData.append("referrer", document.referrer)
      val request = new AjaxRequest(History.url().replacePathAndParams("/clientError"), data = Some(formData))
      request.send()
    }
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
    communicationEntries.attachAndFire { entries =>
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
    val comms = communicationEntries()
    if (comms.isEmpty) {
      scribe.warn("Unable to connect, there are no communication instances.")
    } else {
      val connectionPath = comms.head.path
      // TODO: evaluate supporting more than one in the ClientApplication
      val url = URL(s"$protocol://${window.location.host}$connectionPath")
      webSocket := Some(WebSocketUtil.connect(url, connection))
    }
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