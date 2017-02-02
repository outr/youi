package io.youi.app

import java.io.File

import io.youi.http.{HttpConnection, StringContent}
import io.youi.net.{ContentType, URLMatcher}
import io.youi.server.handler.{CachingManager, HttpHandler, SenderHandler}
import io.youi.stream._

trait Page extends HttpHandler {
  def matcher: URLMatcher
  def resource(httpConnection: HttpConnection): File
  def scalaJSConfig: Option[ScalaJSConfig]
  def cachingManager: CachingManager = CachingManager.Default

  def allowSelectors: Boolean = true
  def deltas(httpConnection: HttpConnection): List[Delta] = scalaJSConfig.map { config =>
    val script =
      s"""
         |<script type="application/javascript" src="${config.path}"></script>
         |<script type="application/javascript">
         |    ${config.function}();
         |</script>
       """.stripMargin
    List(Delta.InsertLastChild(ByTag("body"), script))
  }.getOrElse(Nil)

  override def handle(connection: HttpConnection): Unit = if (matcher.matches(connection.request.url)) {
    val file = resource(connection)
    val parser = HTMLParser.cache(file)
    val selector = if (allowSelectors) connection.request.url.param("selector").map(Selector.parse) else None
    val mods = deltas(connection)
    val html = parser.stream(mods, selector)
    val content = StringContent(html, ContentType.`text/html`, file.lastModified())
    val handler = SenderHandler(content, caching = cachingManager)
    handler.handle(connection)
  }
}

case class ScalaJSConfig(path: String, function: String = "main")