package io.youi.server.handler

import java.io.File

import io.youi.http._
import io.youi.net.{ContentType, URL, URLMatcher}
import io.youi.server.Server
import com.outr.scribe._
import io.youi.stream.{Delta, HTMLParser, Selector}

case class HttpHandlerBuilder(server: Server,
                              urlMatcher: Option[URLMatcher] = None,
                              cachingManager: CachingManager = CachingManager.Default) {
  def matcher(urlMatchers: URLMatcher*): HttpHandlerBuilder = if (urlMatchers.isEmpty) {
    copy(urlMatcher = None)
  } else if (urlMatchers.tail.isEmpty) {
    copy(urlMatcher = Some(urlMatchers.head))
  } else {
    copy(urlMatcher = Some(combined(urlMatchers: _*)))
  }

  def caching(cachingManager: CachingManager): HttpHandlerBuilder = copy(cachingManager = cachingManager)

  def resource(f: => Content): HttpHandler = resource((_: URL) => Some(f))

  def resource(f: URL => Option[Content]): HttpHandler = {
    val handler: HttpHandler = new HttpHandler {
      override def handle(connection: HttpConnection): Unit = if (connection.response.content.isEmpty) {
        if (urlMatcher.forall(_.matches(connection.request.url))) {
          f(connection.request.url).foreach { content =>
            SenderHandler(content, caching = cachingManager).handle(connection)
          }
        }
      }
    }
    server.handlers += handler
    handler
  }

  def file(directory: File, pathTransform: String => String = (s: String) => s): HttpHandler = {
    val handler: HttpHandler = new HttpHandler {
      override def handle(connection: HttpConnection): Unit = if (connection.response.content.isEmpty) {
        if (urlMatcher.forall(_.matches(connection.request.url))) {
          val path = pathTransform(connection.request.url.path.encoded)
          val file = new File(directory, path)
          if (file.isFile) {
            SenderHandler(Content.file(file), caching = cachingManager).handle(connection)
          }
        }
      }
    }
    server.handlers += handler
    handler
  }

  def classLoader(directory: String, pathTransform: String => String = (s: String) => s): HttpHandler = {
    val dir = if (directory.endsWith("/")) {
      directory.substring(directory.length - 1)
    } else {
      directory
    }
    val handler: HttpHandler = new HttpHandler {
      override def handle(connection: HttpConnection): Unit = if (connection.response.content.isEmpty) {
        if (urlMatcher.forall(_.matches(connection.request.url))) {
          val path = pathTransform(connection.request.url.path.encoded)
          Option(getClass.getClassLoader.getResource(s"$dir$path")).foreach { url =>
            val file = new File(url.getFile)
            if (!file.isDirectory) {
              SenderHandler(Content.classPath(url), caching = cachingManager).handle(connection)
            }
          }
        }
      }
    }
    server.handlers += handler
    handler
  }

  def proxy(handler: ProxyHandler): HttpHandler = handle { connection =>
    connection.proxySupport = handler
  }

  def stream(resource: File, allowSelectors: Boolean = false)(deltas: HttpConnection => List[Delta]): HttpHandler = {
    val parser = HTMLParser(resource)
    handle { connection =>
      val selector = if (allowSelectors) connection.request.url.param("selector").map(Selector.parse) else None
      val mods = deltas(connection)
      val html = parser.stream(mods, selector)
      val content = StringContent(html, ContentType.`text/html`, resource.lastModified())
      val handler = SenderHandler(content, caching = cachingManager)
      handler.handle(connection)
    }
  }

  def handle(f: HttpConnection => Unit): HttpHandler = wrap(new HttpHandler {
    override def handle(connection: HttpConnection): Unit = f(connection)
  })

  def wrap(handler: HttpHandler): HttpHandler = {
    val wrapper = new HttpHandler {
      override def handle(connection: HttpConnection): Unit = {
        if (urlMatcher.forall(_.matches(connection.request.url))) {
          handler.handle(connection)
        }
      }
    }
    server.handlers += wrapper
    handler
  }
}