package io.youi.server

import java.io.File

import io.youi.http.{Content, FileContent, HttpConnection, Method, Status, StringContent, URLContent}
import io.youi.net.{ContentType, IP, Path, URLMatcher}
import io.youi.server.handler.{CachingManager, ContentHandler, HttpHandler, SenderHandler}
import io.youi.stream.{ByTag, Delta, HTMLParser, Selector}

import scala.language.implicitConversions
import scala.util.matching.Regex
import scala.xml.Elem

package object dsl {
  private val DeltaKey: String = "deltas"
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

  implicit class HttpConnectionConnectionFilter(val connection: HttpConnection) extends ConnectionFilter {
    override def filter(connection: HttpConnection): Option[HttpConnection] = Some(connection)
  }

  implicit class MethodConnectionFilter(val method: Method) extends ConnectionFilter {
    override def filter(connection: HttpConnection): Option[HttpConnection] = if (connection.request.method == method) {
      Some(connection)
    } else {
      None
    }
  }

  implicit class HttpHandlerFilter(val handler: HttpHandler) extends ConnectionFilter {
    override def filter(connection: HttpConnection): Option[HttpConnection] = {
      handler.handle(connection)
      Some(connection)
    }
  }

  implicit class CachingManagerFilter(val caching: CachingManager) extends ConnectionFilter {
    override def filter(connection: HttpConnection): Option[HttpConnection] = {
      caching.handle(connection)
      Some(connection)
    }
  }

  implicit class DeltasFilter(val deltas: List[Delta]) extends ConnectionFilter {
    override def filter(connection: HttpConnection): Option[HttpConnection] = {
      connection.response.content match {
        case Some(content) => {
          val stream = content match {
            case c: FileContent => HTMLParser.cache(c.file)
            case c: URLContent => HTMLParser.cache(c.url)
            case c: StringContent => HTMLParser.cache(c.value)
          }
          val deltasList = connection.store.getOrElse[List[Delta]](DeltaKey, Nil) ::: deltas

          val selector = connection.request.url.param("selector").map(Selector.parse)
          val streamed = stream.stream(deltasList, selector)
          connection.update { response =>
            response.withContent(Content.string(streamed, content.contentType))
          }
        }
        case None => // No content
      }
      Some(connection)
    }
  }

  implicit class URLMatcherFilter(val matcher: URLMatcher) extends ConnectionFilter {
    override def filter(connection: HttpConnection): Option[HttpConnection] = {
      if (matcher.matches(connection.request.url)) {
        Some(connection)
      } else {
        None
      }
    }
  }

  case class ClassLoaderPath(directory: String = "", pathTransform: String => String = (s: String) => s) extends ConnectionFilter {
    private val dir = if (directory.endsWith("/")) {
      directory.substring(directory.length - 1)
    } else {
      directory
    }

    override def filter(connection: HttpConnection): Option[HttpConnection] = {
      val path = pathTransform(connection.request.url.path.decoded)
      val resourcePath = s"$dir$path" match {
        case s if s.startsWith("/") => s.substring(1)
        case s => s
      }
      Option(getClass.getClassLoader.getResource(resourcePath))
        .map(url => new File(url.getFile))
        .filterNot(_.isDirectory)
        .map { file =>
          SenderHandler(Content.file(file)).handle(connection)
          connection
        }
    }
  }

  object Application extends ConnectionFilter {
    override def filter(connection: HttpConnection): Option[HttpConnection] = {
      val jsDeps = if (applicationJSDepsContent.nonEmpty) {
        s"""<script src="$applicationJSDepsPath"></script>"""
      } else {
        ""
      }
      val applicationDeltas = List(
        Delta.InsertLastChild(ByTag("body"),
          s"""
             |$jsDeps
             |<script src="$applicationJSPath"></script>
             |<script>
             |  application();
             |</script>
           """.stripMargin
        )
      )
      val deltas = connection.store.getOrElse[List[Delta]](DeltaKey, Nil) ::: applicationDeltas
      connection.store(DeltaKey) = deltas
      Some(connection)
    }
  }

  implicit def content2Filter(content: Content): ConnectionFilter = {
    new HttpHandlerFilter(ContentHandler(content, Status.OK))
  }

  implicit def path2AllowFilter(path: Path): ConnectionFilter = PathFilter(path)

  def filters(filters: ConnectionFilter*): ConnectionFilter = ListConnectionFilter(filters.toList)

  def allow(ips: IP*): ConnectionFilter = IPAddressFilter(allow = ips.toList)

  def allow(path: Path): ConnectionFilter = PathFilter(path)

  def respond(content: Content, status: Status = Status.OK): ContentHandler = {
    ContentHandler(content, status)
  }

  implicit def string2Content(value: String): Content = Content.string(value, ContentType.`text/plain`)
  implicit def xml2Content(value: Elem): Content = Content.xml(value, ContentType.`text/xml`)
}