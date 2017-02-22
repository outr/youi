package io.youi.app

import java.io.File

import io.youi.Priority
import io.youi.http.{Content, HttpConnection, HttpRequest}
import io.youi.net.ContentType
import io.youi.server.handler.{CachingManager, HttpHandler, HttpHandlerBuilder, SenderHandler}
import io.youi.stream.{ByTag, Delta, HTMLParser, Selector}

trait SinglePageApplication extends ServerApplication {
  protected def excludeDotHTML: Boolean = true
  protected def templateDirectory: File
  protected def appJSPath: String
  protected def appJSMethod: String
  protected def scriptPaths: List[String] = Nil
  protected def responseMap(httpConnection: HttpConnection): Map[String, String] = Map.empty

  implicit class SinglePageHttpHandlerBuilder(builder: HttpHandlerBuilder) {
    /**
      * Stores deltas on this connection for use serving HTML.
      *
      * @param function the function that takes in an HttpConnection and returns a list of Deltas.
      * @return HttpHandler that has already been added to the server
      */
    def deltas(function: HttpConnection => List[Delta]): HttpHandler = builder.handle { connection =>
      val d = function(connection)
      if (d.nonEmpty) {
        val current = connection.store.getOrElse[List[Delta]](SinglePageApplication.DeltaKey, Nil)
        connection.store(SinglePageApplication.DeltaKey) = current ::: d
      }
    }
  }

  override protected def init(): Unit = {
    super.init()

    handler.priority(Priority.Low).handle { httpConnection =>
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
    val deltas = httpConnection.store.getOrElse[List[Delta]](SinglePageApplication.DeltaKey, Nil)
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
    ) ::: deltas
    val selector = httpConnection.request.url.param("selector").map(Selector.parse)
    val html = stream.stream(d, selector)
    SenderHandler.handle(httpConnection, Content.string(html, ContentType.`text/html`), caching = CachingManager.NotCached)
  }
}

object SinglePageApplication {
  val DeltaKey: String = "deltas"
}