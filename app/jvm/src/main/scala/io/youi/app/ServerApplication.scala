package io.youi.app

import java.io.File

import akka.actor.{ActorSystem, Cancellable}
import io.youi.http._
import io.youi.net.{ContentType, URL}
import io.youi.server.Server
import io.youi.server.handler.{CachingManager, HttpHandler, HttpHandlerBuilder, SenderHandler}
import io.youi.stream.{ByTag, Delta, HTMLParser, Selector}
import io.youi.{JavaScriptError, http}
import net.sf.uadetector.UserAgentType
import net.sf.uadetector.service.UADetectorServiceFactory
import org.powerscala.io._
import profig.ConfigApplication
import reactify.{Channel, Var}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

trait ServerApplication extends YouIApplication with Server with ConfigApplication {
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

  lazy val applicationJSContent: Content = Content.classPathOption(fullOpt).getOrElse(Content.classPath(fastOpt))
  lazy val applicationJSMapContent: Content = Content.classPathOption(fullOptMap).getOrElse(Content.classPath(fastOptMap))
  lazy val applicationJSDepsContent: Option[Content] = Content.classPathOption(jsDeps)

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

    val lastModifiedManager = CachingManager.LastModified()

    // Serve up application.js
    handler.matcher(path.exact("/app/application.js")).caching(lastModifiedManager).resource(applicationJSContent)

    // Serve up application.js.map
    handler.matcher(path.exact("/app/application.js.map")).caching(lastModifiedManager).resource(applicationJSMapContent)

    // Serve up application-jsdeps.js (if available)
    applicationJSDepsContent.foreach { content =>
      handler.matcher(path.exact("/app/application-jsdeps.js")).caching(lastModifiedManager).resource(content)
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

  implicit class AppHandlerBuilder(builder: HttpHandlerBuilder) {
    /**
      * Stores deltas on this connection for use serving HTML.
      *
      * @param function the function that takes in an HttpConnection and returns a list of Deltas.
      * @return HttpHandler that has already been added to the server
      */
    def deltas(function: HttpConnection => List[Delta]): HttpHandler = builder.handle { connection =>
      val d = function(connection)
      addDeltas(connection, d)
    }

    def page(template: Content = ServerApplication.DefaultTemplate): HttpHandler = builder.handle { connection =>
      serveHTML(connection, template)
    }
  }

  def addDeltas(connection: HttpConnection, deltas: List[Delta]): Unit = {
    if (deltas.nonEmpty) {
      val current = connection.store.getOrElse[List[Delta]](ServerApplication.DeltaKey, Nil)
      connection.store(ServerApplication.DeltaKey) = current ::: deltas
    }
  }

  def serveHTML(httpConnection: HttpConnection, content: Content): Unit = {
    val stream = content match {
      case c: FileContent => HTMLParser.cache(c.file)
      case c: URLContent => HTMLParser.cache(c.url)
      case c: StringContent => HTMLParser.cache(c.value)
    }
    val deltas = httpConnection.store.getOrElse[List[Delta]](ServerApplication.DeltaKey, Nil)
    val jsDeps = if (applicationJSDepsContent.nonEmpty) {
      s"""<script src="/app/application-jsdeps.js"></script>"""
    } else {
      ""
    }
    val d = List(
      Delta.InsertLastChild(ByTag("body"),
        s"""
           |$jsDeps
           |<script src="/app/application.js"></script>
           |<script>
           |  application();
           |</script>
         """.stripMargin
      )
    ) ::: deltas
    val selector = httpConnection.request.url.param("selector").map(Selector.parse)
    val html = stream.stream(d, selector)
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

  override protected def run(): Unit = {
    start()
  }

  override def dispose(): Unit = {
    super.dispose()

    system.terminate()
  }
}

object ServerApplication {
  lazy val DefaultTemplate: Content = Content.string(
    """
      |<html>
      |<head>
      | <title></title>
      |</head>
      |<body>
      |</body>
      |</html>
    """.stripMargin.trim, ContentType.`text/html`)

  val DeltaKey: String = "deltas"
}