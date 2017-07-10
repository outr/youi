package io.youi.app

import io.youi.http.{Content, HttpConnection, StringContent}
import io.youi.net.ContentType
import io.youi.server.handler.{CachingManager, HttpProcessor, SenderHandler}
import io.youi.server.validation.Validator
import io.youi.stream._

import scala.collection.mutable.ListBuffer

trait Page extends HttpProcessor[Content] {
  protected def scalaJSConfig: Option[ScalaJSConfig]
  protected def cachingManager: CachingManager = CachingManager.Default

  override protected def validators(httpConnection: HttpConnection): List[Validator] = Nil

  protected def allowSelectors: Boolean = true
  protected def deltas(httpConnection: HttpConnection): List[Delta] = scalaJSConfig.map { config =>
    val script = ListBuffer.empty[String]
    config.jsDepsPath.foreach { path =>
      script += s"""<script type="application/javascript" src="$path"></script>"""
    }
    script += s"""<script type="application/javascript" src="${config.path}"></script>"""
    script += """<script type="application/javascript">"""
    script += s"\t${config.function}();"
    script += "</script>"
    List(Delta.InsertLastChild(ByTag("body"), script.mkString("\n")))
  }.getOrElse(Nil)

  override protected def process(connection: HttpConnection, content: Content): Unit = {
    val parser = HTMLParser.cache(content)
    val selector = if (allowSelectors) connection.request.url.param("selector").map(Selector.parse) else None
    val mods = deltas(connection)
    val html = parser.stream(mods, selector)
    val responseContent = StringContent(html, ContentType.`text/html`, content.lastModified)
    val handler = SenderHandler(responseContent, caching = cachingManager)
    handler.handle(connection)
  }
}

case class ScalaJSConfig(path: String, function: String = "main", jsDepsPath: Option[String] = None)