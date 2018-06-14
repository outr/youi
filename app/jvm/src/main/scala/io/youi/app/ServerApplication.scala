package io.youi.app

import java.io.File

import akka.actor.{ActorSystem, Cancellable}
import io.youi.http._
import io.youi.net.{ContentType, URL}
import io.youi.server.Server
import io.youi.server.handler.{CachingManager, HttpHandler, HttpHandlerBuilder, SenderHandler}
import io.youi.stream.{ByTag, Delta, HTMLParser, Selector}
import io.youi.{JavaScriptError, JavaScriptLog, Priority, http}
import net.sf.uadetector.UserAgentType
import net.sf.uadetector.service.UADetectorServiceFactory
import org.powerscala.io._
import profig.{JsonUtil, Profig}
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

  protected def applicationBasePath: String = "app/application"

  private val fullOpt = s"$applicationBasePath.js"
  private val fastOpt = s"$applicationBasePath-fastopt.js"
  private val fullOptMap = s"$fullOpt.map"
  private val fastOptMap = s"$fastOpt.map"
  private val jsDeps = s"$applicationBasePath-jsdeps.js"

  protected def applicationJSBasePath: String = "/app/application"
  def applicationJSPath: String = s"$applicationJSBasePath.js"
  def applicationJSMapPath: String = s"$applicationJSPath.map"
  def applicationJSDepsPath: String = s"$applicationJSBasePath-jsdeps.js"

  lazy val applicationJSContent: Content = Content.classPathOption(fullOpt).getOrElse(Content.classPath(fastOpt))
  lazy val applicationJSMapContent: Content = Content.classPathOption(fullOptMap).getOrElse(Content.classPath(fastOptMap))
  lazy val applicationJSDepsContent: Option[Content] = Content.classPathOption(jsDeps)

  protected def scriptPaths: List[String] = Nil
  protected def responseMap(httpConnection: HttpConnection): Map[String, String] = Map.empty

  override protected def init(): Unit = {
    super.init()

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
      handler.matcher(path.exact(logPath)).handle { httpConnection =>
        val content = httpConnection.request.content
        content match {
          case Some(requestContent) => requestContent match {
            case formData: FormDataContent => {
              val ip = httpConnection.request.source
              val userAgentString = Headers.Request.`User-Agent`.value(httpConnection.request.headers).getOrElse("")
              val userAgent = userAgentParser.parse(userAgentString)

              // Error logging
              formData.stringOption("error").map(_.value).foreach { jsonString =>
                val jsError = JsonUtil.fromJsonString[JavaScriptError](jsonString)

                val exception = new JavaScriptException(
                  error = jsError,
                  userAgent = userAgent,
                  ip = ip,
                  request = httpConnection.request,
                  info = errorInfo(jsError, httpConnection)
                )
                if (logJavaScriptException(exception)) {
                  error(exception)
                }
              }

              // Message logging
              formData.stringOption("message").map(_.value).foreach { jsonString =>
                val log = JsonUtil.fromJsonString[JavaScriptLog](jsonString)

                scribe.info(s"[JS] ${log.message.trim} (ip: $ip, userAgent: $userAgentString)")
              }
            }
            case otherContent => scribe.error(s"Unsupported content type: $otherContent (${otherContent.getClass.getName})")
          }
          case None => // Ignore
        }

        httpConnection.update(_.withContent(Content.empty))
      }
    }

    val lastModifiedManager = CachingManager.LastModified()

    // Serve up application.js
    handler.matcher(path.exact(applicationJSPath)).caching(lastModifiedManager).resource(applicationJSContent)

    // Serve up application.js.map
    handler.matcher(path.exact(applicationJSMapPath)).caching(lastModifiedManager).resource(applicationJSMapContent)

    // Serve up application-jsdeps.js (if available)
    applicationJSDepsContent.foreach { content =>
      handler.matcher(path.exact(applicationJSDepsPath)).caching(lastModifiedManager).resource(content)
    }
  }

  def addTemplate(directory: File,
                  mappings: Set[HttpConnection => Option[File]] = Set.empty,
                  excludeDotHTML: Boolean = true,
                  deltas: List[Delta] = Nil,
                  includeApplication: URL => Boolean = _ => true): HttpHandler = {
    // Serve up template files
    handler.priority(Priority.Low).handle { httpConnection =>
      if (httpConnection.response.content.isEmpty) {
        val url = httpConnection.request.url
        val fileName = url.path.decoded
        if (fileName.endsWith(".html") && excludeDotHTML) {
          // Ignore
        } else {
          val exactFile = new File(directory, fileName)
          var file: File = exactFile
          if (excludeDotHTML && !file.exists()) {
            file = new File(directory, s"$fileName.html")
          }
          if (!file.isFile) { // Handle mappings
            mappings.toStream.flatMap(m => m(httpConnection)).find(_.isFile).foreach(file = _)
          }

          if (file.isFile) {
            if (file.getName.endsWith(".html")) {
              CachingManager.NotCached.handle(httpConnection)
              serveHTML(httpConnection, file, deltas, includeApplication(url))
            } else {
              CachingManager.LastModified().handle(httpConnection)
              httpConnection.update(_.withContent(Content.file(file)))
            }
          }
        }
      }
    }
  }

  protected val cancellable: Option[Cancellable] = Some(system.scheduler.schedule(30.seconds, 30.seconds) {
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

  implicit class AppHandlerBuilder(builder: HttpHandlerBuilder) {
    /**
      * Stores deltas on this connection for use serving HTML.
      *
      * @param function the function that takes in an HttpConnection and returns a list of Deltas.
      * @return HttpHandler that has already been added to the server
      */
    def deltas(function: HttpConnection => List[Delta]): HttpHandler = builder.handle { connection =>
      val d: List[Delta] = function(connection)
      connection.deltas += d
    }

    def page(template: Content = ServerApplication.CanvasTemplate,
             deltas: List[Delta] = Nil,
             includeApplication: Boolean = true): HttpHandler = builder.handle { connection =>
      serveHTML(connection, template, deltas, includeApplication)
    }
  }

  override protected def handleInternal(connection: HttpConnection): Unit = {
    super.handleInternal(connection)

    connection.response.content match {
      case Some(content) if content.contentType == ContentType.`text/html` && connection.deltas.nonEmpty => {
        connection.update(_.removeContent())
        serveHTML(connection, content, Nil, includeApplication = false)
      }
      case _ => // Ignore
    }
  }

  def serveHTML(httpConnection: HttpConnection, content: Content, deltas: List[Delta], includeApplication: Boolean): Unit = {
    val stream = content match {
      case c: FileContent => HTMLParser.cache(c.file)
      case c: URLContent => HTMLParser.cache(c.url)
      case c: StringContent => HTMLParser.cache(c.value)
    }
    val responseFields = responseMap(httpConnection).toList.map {
      case (name, value) => s"""<input type="hidden" id="$name" value="$value"/>"""
    }
    val deltasList = httpConnection.deltas() ::: deltas
    val applicationDeltas = if (includeApplication) {
      val jsDeps = if (applicationJSDepsContent.nonEmpty) {
        s"""<script src="$applicationJSDepsPath"></script>"""
      } else {
        ""
      }
      List(
        Delta.InsertLastChild(ByTag("body"),
          s"""
             |${scriptPaths.map(p => s"""<script src="$p"></script>""").mkString("\n")}
             |${responseFields.mkString("\n")}
             |$jsDeps
             |<script src="$applicationJSPath"></script>
             |<script>
             |  application();
             |</script>
           """.stripMargin
        )
      )
    } else {
      Nil
    }
    val d = applicationDeltas ::: deltasList
    val selector = httpConnection.request.url.param("selector").map(Selector.parse)
    val html = stream.stream(d, selector)
    httpConnection.deltas.clear()
    SenderHandler.handle(httpConnection, Content.string(html, ContentType.`text/html`), caching = CachingManager.NotCached)
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
    Profig.loadDefaults()
    Profig.merge(args)
    start()
  }

  override def dispose(): Unit = {
    super.dispose()

    system.terminate()
  }
}

object ServerApplication {
  /**
    * Empty page template with overflow on the body disabled.
    */
  lazy val CanvasTemplate: Content = Content.string(
    """
      |<html>
      |<head>
      | <title></title>
      | <style>
      |   body {
      |     margin: 0;
      |     overflow: hidden;
      |     width: 100vw;
      |     height: 100vh;
      |   }
      | </style>
      |</head>
      |<body>
      |</body>
      |</html>
    """.stripMargin.trim, ContentType.`text/html`)

  /**
    * Empty page template with overflow on the body disabled and viewport fixed to avoid zooming.
    */
  lazy val AppTemplate: Content = Content.string(
    """
      |<html>
      |<head>
      | <title></title>
      | <!-- Required meta tags -->
      | <meta charset="utf-8">
      | <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      |
      | <!-- Bootstrap CSS -->
      | <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
      | <style>
      |   body {
      |     margin: 0;
      |     overflow: hidden;
      |     width: 100vw;
      |     height: 100vh;
      |   }
      | </style>
      | <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
      | <meta name="HandheldFriendly" content="true" />
      |</head>
      |<body>
      |<!-- Scripts -->
      |<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
      |<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
      |<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
      |</body>
      |</html>
    """.stripMargin.trim, ContentType.`text/html`)
}