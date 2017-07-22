package io.youi.app

import java.io.File
import java.net.URL

import io.youi.Priority
import io.youi.http.{Content, FileContent, HttpConnection, StringContent, URLContent, path}
import io.youi.net.ContentType
import io.youi.server.handler.{CachingManager, HttpHandler, HttpHandlerBuilder, SenderHandler}
import io.youi.stream.{ByTag, Delta, HTMLParser, Selector}

@deprecated("Use ServerApplication instead.", "0.5.0")
trait SinglePageApplication extends ServerApplication {
  protected def excludeDotHTML: Boolean = true
  protected def templateDirectory: File
  protected def appJSContent: Content
  protected def appJSDepsContent: Option[Content] = None
  protected def appJSMapContent: Content = appJSContent match {
    case c: FileContent => c.copy(file = new File(c.file.getParentFile, s"${c.file.getName}.map"))
    case c: URLContent => c.copy(url = new URL(s"${c.url.toString}.map"))
    case c => throw new RuntimeException(s"Unsupported Content $c. You must directly override the appJSMapContent method.")
  }
  protected def appJSFileName: String = s"${getClass.getSimpleName.replaceAllLiterally("$", "").toLowerCase}"
  protected def appJSMethod: String
  protected def scriptPaths: List[String] = Nil
  protected def responseMap(httpConnection: HttpConnection): Map[String, String] = Map.empty

  private var mappings = Set.empty[HttpConnection => Option[File]]

  def addMapping(mapper: HttpConnection => Option[File]): Unit = synchronized {
    mappings += mapper
  }

  override protected def init(): Unit = {
    super.init()

    val neverCacheManager = CachingManager.NotCached
    val lastModifiedManager = CachingManager.LastModified()

    handler.matcher(path.exact(s"/$appJSFileName.js")).caching(lastModifiedManager).resource(appJSContent)
    handler.matcher(path.exact(s"/$appJSFileName.js.map")).caching(lastModifiedManager).resource(appJSMapContent)
    appJSDepsContent.foreach { content =>
      handler.matcher(path.exact(s"/$appJSFileName-jsdeps.js")).caching(lastModifiedManager).resource(content)
    }

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
            neverCacheManager.handle(httpConnection)
            serveHTML(httpConnection, file)
          } else {
            lastModifiedManager.handle(httpConnection)
            httpConnection.update(_.withContent(Content.file(file)))
          }
        }
      }
    }
  }
}