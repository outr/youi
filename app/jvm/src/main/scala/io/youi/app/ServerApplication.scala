package io.youi.app

import fabric.parse.Json

import java.io.File
import io.youi.http._
import io.youi.http.content._
import io.youi.net._
import io.youi.server.Server
import io.youi.server.handler.{CachingManager, HttpHandler, HttpHandlerBuilder, SenderHandler}
import io.youi.stream.delta.Delta
import io.youi.stream.{HTMLParser, Selector, _}
import io.youi.{JavaScriptError, JavaScriptLog, http}
import net.sf.uadetector.UserAgentType
import net.sf.uadetector.service.UADetectorServiceFactory
import fabric.rw._
import reactify.Var
import scribe._
import scribe.data.MDC

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}

trait ServerApplication extends YouIApplication with Server {
  override def isClient: Boolean = false

  override def isServer: Boolean = true

  lazy val cacheDirectory: Var[File] = Var(new File(System.getProperty("user.home"), ".cache"))

  private lazy val userAgentParser = UADetectorServiceFactory.getResourceModuleParser

  protected def applicationBasePath: String = "app/application"

  private val fullOpt = s"$applicationBasePath.js.youi"
  private val fastOpt = s"$applicationBasePath-fastopt.js.youi"
  private val fullOptMap = s"$fullOpt.map"
  private val fastOptMap = s"$fastOpt.map"
  private val jsDeps = s"$applicationBasePath-jsdeps.js.youi"

  protected def applicationJSBasePath: String = "/app/application"
  def applicationJSPath: String = s"$applicationJSBasePath.js"
  def applicationJSMapPath: String = s"$applicationJSPath.map"
  def applicationJSDepsPath: String = s"$applicationJSBasePath-jsdeps.js"

  lazy val applicationJSContent: Content = Content.classPathOption(fullOpt).getOrElse(Content.classPath(fastOpt))
  lazy val applicationJSMapContent: Content = Content.classPathOption(fullOptMap).getOrElse(Content.classPath(fastOptMap))
  lazy val applicationJSDepsContent: Option[Content] = Content.classPathOption(jsDeps)

  protected def scriptPaths: List[String] = Nil
  protected def responseMap(httpConnection: HttpConnection): Map[String, String] = Map.empty

  override protected def init(): Future[Unit] = super.init().map { _ =>
    handler.resource { url =>
      val path = url.path.encoded match {
        case "/" => None
        case s if s.startsWith("/") => Some(s.substring(1))
        case s => Some(s)
      }
      path.flatMap(p => Content.classPathOption(s"assets/$p"))
    }
    handler.matcher(path.exact(path"/wrap-image")).handle { httpConnection =>
      Future.successful(
        httpConnection.modify { response =>
          val imageURL = httpConnection.request.url.param("src").getOrElse("")
          response.withContent(Content.string(
            s"""<html>
              |<head>
              |<title>Wrap Image</title>
              |</head>
              |<body>
              |<img src="$imageURL"/>
              |</body>
              |</html>""".stripMargin, ContentType.`text/html`))
        }
      )
    }
    if (logJavaScriptErrors) {
      handler.matcher(path.exact(logPath)).handle { httpConnection =>
        val content = httpConnection.request.content
        content match {
          case Some(requestContent) => requestContent match {
            case formData: FormDataContent => {
              val ip = httpConnection.request.originalSource
              val userAgentString = Headers.Request.`User-Agent`.value(httpConnection.request.headers).getOrElse("")
              val userAgent = userAgentParser.parse(userAgentString)

              // Error logging
              formData.stringOption("error").map(_.value).foreach { jsonString =>
                val jsError = Json.parse(jsonString).as[JavaScriptError]

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
                val log = Json.parse(jsonString).as[JavaScriptLog]

                MDC.contextualize("ip", ip.toString) {
                  MDC.contextualize("userAgent", userAgentString) {
                    scribe.info(s"[JS] ${log.message.trim}")
                  }
                }
              }
            }
            case otherContent => scribe.error(s"Unsupported content type: $otherContent (${otherContent.getClass.getName})")
          }
          case None => // Ignore
        }

        Future.successful(httpConnection.modify(_.withContent(Content.empty)))
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

  def addTemplate(lookup: String => Option[Content],
                  mappings: Set[HttpConnection => Option[Content]] = Set.empty,
                  excludeDotHTML: Boolean = true,
                  deltas: List[Delta] = Nil,
                  includeApplication: URL => Boolean = _ => true): HttpHandler = {
    // Serve up template files
    handler.priority(Priority.Low).handle { httpConnection =>
      if (httpConnection.response.content.isEmpty) {
        val url = httpConnection.request.url
        val fileName = url.path.decoded
        val mapped = mappings.flatMap(_ (httpConnection)).headOption
        val content = lookup(fileName).orElse { if (excludeDotHTML) {
          lookup(s"$fileName.html")
        } else {
          None
        }}
        mapped.orElse(content).map { content =>
          if (content.contentType == ContentType.`text/html`) {
            CachingManager.NotCached.handle(httpConnection)
            serveHTML(httpConnection, content, deltas, includeApplication(url))
          } else {
            CachingManager.LastModified().handle(httpConnection)
            Future.successful(httpConnection.modify(_.withContent(content)))
          }
        }.getOrElse(Future.successful(httpConnection))
      } else {
        Future.successful(httpConnection)
      }
    }
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

  implicit class AppHandlerBuilder(builder: HttpHandlerBuilder) {
    /**
      * Stores deltas on this connection for use serving HTML.
      *
      * @param function the function that takes in an HttpConnection and returns a list of Deltas.
      * @return HttpHandler that has already been added to the server
      */
    def deltas(function: HttpConnection => List[Delta]): HttpHandler = builder.handle { connection =>
      val d: List[Delta] = function(connection)
      connection.deltas ++= d
      Future.successful(connection)
    }

    def page(template: Content = ServerApplication.AppTemplate,
             deltas: List[Delta] = Nil,
             includeApplication: Boolean = true): HttpHandler = builder.handle { connection =>
      serveHTML(connection, template, deltas, includeApplication)
    }
  }

  override protected def handleInternal(connection: HttpConnection): Future[HttpConnection] = {
    super.handleInternal(connection).flatMap { c =>
      c.response.content match {
        case Some(content) if content.contentType == ContentType.`text/html` && c.deltas.nonEmpty => {
          serveHTML(c.modify(_.removeContent()), content, Nil, includeApplication = false)
        }
        case _ => Future.successful(c) // Ignore
      }
    }
  }

  def serveHTML(httpConnection: HttpConnection, content: Content, deltas: List[Delta], includeApplication: Boolean): Future[HttpConnection] = {
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
        Delta.InsertLastChild(Selector.ByTag("body"),
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

  // Creates a cached version of the URL and adds an explicit matcher to serve it
  override def cached(url: URL): String = {
    val path = url.asPath()
    val directory = cacheDirectory()
    val file = new File(directory, path)
    file.getParentFile.mkdirs()
    Stream.apply(new java.net.URL(url.toString), file)
    val content = Content.file(file)
    handler.matcher(http.path.exact(path)).resource(content)
    path
  }
}

object ServerApplication {
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
      | <meta name="viewport" content="width=device-width, height=device-height, user-scalable=no">
      | <meta name="msapplication-tap-highlight" content="no">
      | <meta name="apple-mobile-web-app-capable" content="yes">
      | <meta name="apple-mobile-web-app-status-bar-style" content="default">
      | <script src="/source-map.min.js"></script>
      | <style>
      |   body {
      |     margin: 0;
      |     overflow: hidden;
      |     width: 100%;
      |     height: 100%;
      |   }
      |   :focus {
      |     outline: none;
      |   }
      | </style>
      |</head>
      |<body>
      |</body>
      |</html>
    """.stripMargin.trim, ContentType.`text/html`)
}