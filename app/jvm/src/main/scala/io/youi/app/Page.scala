package io.youi.app

import java.io.File

import io.youi.http.{HttpConnection, StringContent}
import io.youi.net.ContentType
import io.youi.server.handler.{CachingManager, HttpProcessor, SenderHandler}
import io.youi.server.validation.Validator
import io.youi.stream._

trait Page extends HttpProcessor[File] {
  protected def scalaJSConfig: Option[ScalaJSConfig]
  protected def cachingManager: CachingManager = CachingManager.Default

  override protected def validators: List[Validator] = Nil

  protected def allowSelectors: Boolean = true
  protected def deltas(httpConnection: HttpConnection): List[Delta] = scalaJSConfig.map { config =>
    val script =
      s"""
         |<script type="application/javascript" src="${config.path}"></script>
         |<script type="application/javascript">
         |    ${config.function}();
         |</script>
       """.stripMargin
    List(Delta.InsertLastChild(ByTag("body"), script))
  }.getOrElse(Nil)

  override protected def process(connection: HttpConnection, file: File): Unit = {
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