package io.youi.app

import java.io.File

import io.youi.http.{Content, HttpConnection}
import io.youi.net.ContentType
import io.youi.server.handler.{CachingManager, SenderHandler}
import io.youi.stream.{ByTag, Delta, HTMLParser, Selector}

trait SinglePageApplication extends ServerApplication {
  protected def excludeDotHTML: Boolean = true
  protected def templateDirectory: File
  protected def appJSPath: String
  protected def appJSMethod: String
  protected def scriptPaths: List[String] = Nil
  protected def responseMap(httpConnection: HttpConnection): Map[String, String] = Map.empty
  protected def deltas(httpConnection: HttpConnection): List[Delta] = Nil

  override protected def init(): Unit = {
    super.init()

    handler.handle { httpConnection =>
      val url = httpConnection.request.url
      val fileName = url.path.decoded
      if (fileName.endsWith(".html") && excludeDotHTML) {
        // Ignore
      } else {
        val exactFile = new File(templateDirectory, fileName)
        var file: File = exactFile
        if (excludeDotHTML && !file.exists()) {
          file = new File(templateDirectory, s"$fileName.html")
        }

        if (file.exists() && file.isFile) {
          if (file.getName.endsWith(".html")) {
            serveHTML(httpConnection, file)
          } else {
            httpConnection.update(_.withContent(Content.file(file)))
          }
        }
      }
    }
  }

  protected def serveHTML(httpConnection: HttpConnection, htmlFile: File): Unit = {
    val stream = HTMLParser.cache(htmlFile)
    val responseFields = responseMap(httpConnection).toList.map {
      case (name, value) => s"""<input type="hidden" id="$name" value="$value"/>"""
    }
    val d = List(
      Delta.InsertLastChild(ByTag("body"),
        s"""
           |${scriptPaths.map(p => s"""<script src="$p"></script>""").mkString("\n")}
           |${responseFields.mkString("\n")}
           |<script src="$appJSPath"></script>
           |<script>
           |  $appJSMethod();
           |</script>
         """.stripMargin
      )
    ) ::: deltas(httpConnection)
    val selector = httpConnection.request.url.param("selector").map(Selector.parse)
    val html = stream.stream(d, selector)
    SenderHandler.handle(httpConnection, Content.string(html, ContentType.`text/html`), caching = CachingManager.NotCached)
  }
}
