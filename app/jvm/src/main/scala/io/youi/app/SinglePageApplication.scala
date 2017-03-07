package io.youi.app

import java.io.File

import io.youi.Priority
import io.youi.http.{Content, FileContent, HttpConnection, StringContent, URLContent, path}
import io.youi.net.ContentType
import io.youi.server.handler.{CachingManager, HttpHandler, HttpHandlerBuilder, SenderHandler}
import io.youi.stream.{ByTag, Delta, HTMLParser, Selector}

trait SinglePageApplication extends ServerApplication {
  protected def excludeDotHTML: Boolean = true
  protected def templateDirectory: File
  protected def appJSContent: Content
  protected def appJSFileName: String = s"${getClass.getSimpleName.replaceAllLiterally("$", "").toLowerCase}.js"
  protected def appJSMethod: String
  protected def scriptPaths: List[String] = Nil
  protected def responseMap(httpConnection: HttpConnection): Map[String, String] = Map.empty

  private var mappings = Set.empty[HttpConnection => Option[File]]

  implicit class SinglePageHttpHandlerBuilder(builder: HttpHandlerBuilder) {
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

    def htmlPage(template: Content = SinglePageApplication.DefaultTemplate): HttpHandler = builder.handle { connection =>
      serveHTML(connection, template)
    }
  }

  def addDeltas(connection: HttpConnection, deltas: List[Delta]): Unit = {
    if (deltas.nonEmpty) {
      val current = connection.store.getOrElse[List[Delta]](SinglePageApplication.DeltaKey, Nil)
      connection.store(SinglePageApplication.DeltaKey) = current ::: deltas
    }
  }

  def addMapping(mapper: HttpConnection => Option[File]): Unit = synchronized {
    mappings += mapper
  }

  override protected def init(): Unit = {
    super.init()

    handler.matcher(path.exact(s"/$appJSFileName")).resource(appJSContent)
    // TODO: add js.map

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
        if (!file.isFile) {     // Handle mappings
          mappings.toStream.flatMap(m => m(httpConnection)).find(_.isFile).foreach(file = _)
        }

        if (file.isFile) {
          if (file.getName.endsWith(".html")) {
            serveHTML(httpConnection, file)
          } else {
            httpConnection.update(_.withContent(Content.file(file)))
          }
        }
      }
    }
  }

  def serveHTML(httpConnection: HttpConnection, content: Content): Unit = {
    val stream = content match {
      case c: FileContent => HTMLParser.cache(c.file)
      case c: URLContent => HTMLParser.cache(c.url)
      case c: StringContent => HTMLParser.cache(c.value)
    }
    val responseFields = responseMap(httpConnection).toList.map {
      case (name, value) => s"""<input type="hidden" id="$name" value="$value"/>"""
    }
    val deltas = httpConnection.store.getOrElse[List[Delta]](SinglePageApplication.DeltaKey, Nil)
    val d = List(
      Delta.InsertLastChild(ByTag("body"),
        s"""
           |${scriptPaths.map(p => s"""<script src="$p"></script>""").mkString("\n")}
           |${responseFields.mkString("\n")}
           |<script src="/$appJSFileName"></script>
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